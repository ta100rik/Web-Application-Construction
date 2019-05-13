window.onload = function(){
    initPage();
    loadCountries();
};
function initPage(){
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", "https://ipapi.co/json", false ); // false for synchronous request
    xmlHttp.send( null );
    var json = JSON.parse(xmlHttp.responseText);
    document.getElementById("Landcode").innerText = json.country;
    document.getElementById("Land").innerText = json.country_name;
    document.getElementById("Region").innerText = json.region;
    document.getElementById("Stad").innerText = json.city;
    document.getElementById("Postcode").innerText = json.postal;
    document.getElementById("Latitude").innerText = json.latitude;
    document.getElementById("Longitutde").innerText = json.longitude;
    document.getElementById("IP").innerText = json.ip;
    // console.log(json);
    getWheather(json);
}

function getWheather(json){
    let url = `http://api.openweathermap.org/data/2.5/weather?lat=${json.latitude}&lon=${json.longitude}&appid=5a0c9fe48ebd7415f3c6a10135f7b60c`;
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", url, false ); // false for synchronous request
    xmlHttp.send( null );
    let data = JSON.parse(xmlHttp.responseText);
    // console.log(data);
    //appid
    document.getElementById("Temperatuur").innerText = data.main.temp;
    document.getElementById("Luchtvochtigheid").innerText = data.main.humidity;
    document.getElementById("Windsnelheid").innerText = data.wind.speed;
    document.getElementById("Windrichting").innerText = data.wind.deg;
    document.getElementById("Zonsopgang").innerText = data.sys.sunrise;
    document.getElementById("Zonsondergang").innerText = data.sys.sunset;
}

function appendchild(html){
    let selector = document.getElementById("countries");
    // console.log(html);
    selector.innerHTML  = selector.innerHTML +  html;
    var table = document.getElementById("countries");
    var trList = table.getElementsByTagName("tr");
    for (let i = 0;i < trList.length;i++){
        trList[i].onclick = function(){
            let object  = {};
            object.longitude    =  trList[i].getAttribute("data-long");
            object.latitude      = trList[i].getAttribute("data-lat");
            getWheather(object);
        };
    }

}
function loadCountries(){
    fetch("Rest_Service/countries")
        .then(response => response.json())
        .then(data => {
            for (let i of data){
                let html = `
                    <tr data-long="${i.Longtitude}" data-lat="${i.Latitude}">
                        <td>${i.Name}</td>
                        <td>${i.Capital}</td>
                        <td>${i.Region}</td>
                        <td>${i.Surface}</td>
                        <td>${i.Population}</td>
                               
                    </tr>`;
                appendchild(html);
            }
        })
}
