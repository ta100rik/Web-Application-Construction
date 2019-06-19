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
    document.getElementById("Stad").onclick = function () {
        initPage();

    }
    getWheather(json);
}

function getWheather(json){
    // console.log(localStorage.getItem(json.country_name));
    if(localStorage.getItem(json.country_name)=== null){
        let date = new Date();
        let url = `https://api.openweathermap.org/data/2.5/weather?lat=${json.latitude}&lon=${json.longitude}&appid=5a0c9fe48ebd7415f3c6a10135f7b60c`;
        let xmlHttp = new XMLHttpRequest();
        xmlHttp.open( "GET", url, false ); // false for synchronous request
        xmlHttp.send( null );
        var data = JSON.parse(xmlHttp.responseText);
        data["time"] = date.getTime();
        localStorage.setItem(json.country_name, JSON.stringify(data));
        console.log("setting and remote");
    }else{

        let localdata = JSON.parse(localStorage.getItem(json.country_name));
        let date = new Date();
        times = date.getTime();
        if((times - localdata.time) > 600000){
            let date = new Date();
            let url = `https://api.openweathermap.org/data/2.5/weather?lat=${json.latitude}&lon=${json.longitude}&appid=5a0c9fe48ebd7415f3c6a10135f7b60c`;
            let xmlHttp = new XMLHttpRequest();
            xmlHttp.open( "GET", url, false ); // false for synchronous request
            xmlHttp.send( null );
            var data = JSON.parse(xmlHttp.responseText);
            data["time"] = date.getTime();
            localStorage.setItem(json.country_name, JSON.stringify(data));
        }else{
            var data = localdata;
        }
        // let url = `http://api.openweathermap.org/data/2.5/weather?lat=${json.latitude}&lon=${json.longitude}&appid=5a0c9fe48ebd7415f3c6a10135f7b60c`;
        // let xmlHttp = new XMLHttpRequest();
        // xmlHttp.open( "GET", url, false ); // false for synchronous request
        // xmlHttp.send( null );
        // console.log(data);
        // console.log("local");
    }
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
        trList[i].onclick = function(e){
            let object  = {};


            if(e.srcElement.nodeName != "BUTTON"){


            object.longitude    =  trList[i].getAttribute("data-long");
            object.country_name =  trList[i].getAttribute("data-country");
            object.latitude     = trList[i].getAttribute("data-lat");
            getWheather(object);
            }
        };
    }

}
function loadCountries(){
    let selector = document.getElementById("countries");
    // console.log(html);
    selector.innerHTML  = "";
    fetch("/api/test")
        .then(response => response.json())
        .then(data => {
            for (let i of data){
                // console.log(i);
                let html = `
                    <tr id="${i.Code}" data-country="${i.Name}" data-long="${i.Longtitude}" data-lat="${i.Latitude}">
                        <td>${i.Name}</td>
                        <td>${i.Capital}</td>
                        <td>${i.Region}</td>
                        <td>${i.Surface}</td>
                        <td>${i.Population}</td>
                        <td><button onclick="Edit('${i.Code.trim()}')">Edit</button><button onclick="remove('${i.Code.trim()}')">remove</button></td>
                    </tr>`;
                appendchild(html);
            }
        })
}

function remove(id){
    console.log(id);
    fetch("api/test" + "/" + id, {
        method: 'DELETE'
    }).then(() => {
        document.getElementById(id).remove();
    }).catch(err => {
        console.error(err)
    })
}
function showinsert(){
    document.getElementById("modal2").style = "display:block";
}
function Edit(id){
    childeren = document.getElementById(id).childNodes;
    document.getElementById("modal").style = "display:block";
    let array = ["landval" ,"Hoofdstad" ,"Regio" ,"Oppervlakte" ,"Inwoners"];
    let counter = 1;
    document.getElementById("code").value = id;
    for (let i of childeren){
        if(counter < 12){
            if(i.nodeName == "TD"){
                // console.log(i);
                let identifier = (counter / 2) - 1;
                document.getElementById(array[identifier]).value = i.innerText;
            }
        }
        counter ++;
    }
}

function update() {
    const element = document.getElementById("updateform");
    const formData      = new FormData(element);
    const encodeData    = new URLSearchParams(formData);
    console.log(formData);
    console.log(encodeData);
    console.log(encodeData.values());

    fetch("api/test/", {
        method: 'PUT',
        body: encodeData
    }).then(data =>hidden(data,"modal"))
        .then(()=> loadCountries())
}
function insert() {
    const element = document.getElementById("insertform");
    const formData      = new FormData(element);
    const encodeData    = new URLSearchParams(formData);

    fetch("api/test/", {
        method: 'POST',
        body: encodeData
    }).then(data => hidden(data,"modal2"))
        .then(()=> loadCountries())
}

function hidden(data,id){
    console.log(data);
    const element = document.getElementById(id);
    element.style = "display:none";

    // loadCountries();
}