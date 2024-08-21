let allTournee = []
let tournee = {}
let tourneeCounter = 0;

// 将数据转成字符串存储到 localStorage
function saveToLocalStorage(key, data) {
    localStorage.setItem(key, JSON.stringify(data));
}

// 从 localStorage 获取数据 (对象)
function getFromLocalStorage(key) {
    const data = localStorage.getItem(key);
    return JSON.parse(data);
}

// 从 localStorage 移除数据
function removeFromLocalStorage(key) {
    localStorage.removeItem(key);
}

// 获取城市数据并保存到 localStorage
function getCitiesAndSave() {
    const requestOption = {
        headers: { 'Content-Type': 'application/json' },
    };
    fetch(window.location.origin + "/api/rest/villes/allCities", requestOption)
        .then(response => response.json())
        .then(cities => {
            if (cities && cities.length > 0) {
                saveToLocalStorage("cities", cities);
                saveToLocalStorage("citiesForTournee", cities);
                //显示所有城市
                displayCities(cities);

                const villesAssociees = document.querySelector('.container-tournee2 .table');


                //                localStorage.setItem("cities", JSON.stringify(villesAssociees));

                // 添加事件监听器
                const ajouterButtons = document.querySelectorAll('.btn-success.little-btn.addCity');
                ajouterButtons.forEach(button => {

                    addCityEventListener(button);
                });
            } else {
                incorrectLoginDisplay();
            }
        })
        .catch(error => {
            console.log("Une erreur s'est produite lors de la récupération des villes:", error);
        });
}

// 从 localStorage 获取城市数据并显示在界面上
function displayCitiesFromLocalStorage() {
    const cities = getFromLocalStorage("cities");
    if (cities && cities.length > 0) {
        displayCities(cities);
    } else {
        incorrectLoginDisplay();
    }
}

// 从 localStorage 移除城市数据
function removeCitiesFromLocalStorage() {
    removeFromLocalStorage("cities");
}

// 将城市数据显示在界面上
function displayCities(cities) {
    const citiesContainer = document.getElementById("city");
    if (citiesContainer) {
        citiesContainer.innerHTML = "";
        cities.forEach(city => {
            citiesContainer.innerHTML += `
                <div class="table-line-container villeAlivrer" city="${city.id}">
                   <button class="btn btn-success little-btn addCity">ajouter</button>
                   <span class="table-text" name="ville">${city.cityName}</span>
                </div>
            `;
        });

        // 添加从容器2返回容器1的事件监听器
        const deleteButtons = citiesContainer.querySelectorAll('.delete-img');
        deleteButtons.forEach(button => {
            button.addEventListener('click', function () {
                const cityContainer = this.parentElement.parentElement;
                const cityName = cityContainer.querySelector('.table-text[name="ville"]').textContent;

                // 创建新的城市元素并添加到 "villes à livrer" 区域
                const newCityElement = document.createElement('div');
                newCityElement.classList.add('table-line-container', 'villeAlivrer');
                newCityElement.innerHTML = `
                    <button class="btn btn-success little-btn addCity">ajouter</button>
                    <span class="table-text" name="ville">${cityName}</span>
                `;
                const villesALivrer = document.querySelector('.container-tournee2 .table');
                villesALivrer.appendChild(newCityElement);

                // 从 "villes associées a la tournée" 区域移除相应的城市元素
                cityContainer.remove();

                // 重新添加事件监听器
                addCityEventListener(newCityElement.querySelector('.addCity'));
            });
        });
    }
}

// 添加城市按钮的事件监听器
function addCityEventListener(button) {
    button.addEventListener('click', function () {

        console.log("ceci est un test");
        const cityContainer = this.parentElement;
        const cityName = cityContainer.querySelector('.table-text[name="ville"]').textContent;
        console.log(cityContainer);

        // 创建新的城市元素并添加到 "villes associées a la tournée" 区域
        const newCityElement = document.createElement('div');
        newCityElement.classList.add('table-line-container', 'city-container', 'ass');
        newCityElement.setAttribute("city", cityContainer.getAttribute("city"))
        newCityElement.innerHTML = `
            <span class="table-text">${cityName}</span>
            <div class="table-line-image"><img class="delete-img" src="./imgs/plus.svg" alt=""></div>
        `;
        const villesAssociees = document.querySelector('.container-tournee2 .table');
        villesAssociees.appendChild(newCityElement);

        // 从 "villes à livrer" 区域移除相应的城市元素
        cityContainer.remove();

        // 添加从容器2返回容器1的事件监听器
        const deleteImg = newCityElement.querySelector('.delete-img');
        deleteImg.addEventListener('click', function () {
            const cityContainer = this.parentElement.parentElement;
            const cityName = cityContainer.querySelector('.ass .table-text').textContent;

            // 创建新的城市元素并添加到 "villes à livrer" 区域
            const newCityElement = document.createElement('div');
            newCityElement.classList.add('table-line-container', 'villeAlivrer');
            newCityElement.setAttribute("city", cityContainer.getAttribute("city"))
            newCityElement.innerHTML = `
                <button class="btn btn-success little-btn addCity">ajouter</button>
                <span class="table-text" name="ville">${cityName}</span>
            `;
            const villesALivrer = document.getElementById('city');
            villesALivrer.appendChild(newCityElement);

            // 从 "villes associées a la tournée" 区域移除相应的城市元素
            cityContainer.remove();

            // 重新添加事件监听器
            addCityEventListener(newCityElement.querySelector('.addCity'));

            //获取更新后的城市数据
            /* const updatedCities = Array.from(
                citiesContainer.querySelectorAll('.table-text[name="ville"]'))
                .map(city => ({ cityName: city.textContent })); */




        });



    });
}

document.getElementById("ajouterTourneeButton").addEventListener("click", function () {
    console.log("entrée eventListener");
    let arrayOfcities = document.querySelectorAll(".ass");
    console.log(arrayOfcities);
    let array = []
    if (arrayOfcities.length != 0) {
        for (i = 0; i < arrayOfcities.length; i++) {
            console.log(i);
            array.push(Number(arrayOfcities[i].getAttribute("city")))
        }
    }

    tournee.cities = array
    tournee.deliveryMan = Number(document.getElementById("deliveryMan").value)
    tournee.date = localStorage.getItem("dateForTournee")

    allTournee.push(tournee)


    document.querySelector(".associateCities").innerHTML = "";

    /*  const tourneeElement = document.createElement('div');
    tourneeElement.classList.add('table','tournees-pretes');
     const tourneeArray = document.querySelector('.table tournees-pretes');
     tourneeArray.appendChild(tourneeElement); */
    const tourneeElement = document.getElementById("tournees-pretes");

    tourneeElement.innerHTML += `
                    <div class="table-line-container tournee" tournee="${tourneeCounter}">
                    <span class="table-text">tournée ${tourneeCounter + 1}</span>
                    <div class="flex">
                        <button class="little-btn btn btn-warning bg-warning"> <span>modifier</span> </button>
                        <button class="little-btn btn btn-danger bg-danger"> supprimer </button>
                    </div>
                </div> 
    `;
    tourneeCounter++


    tournee = {}

})

document.getElementById("termine").addEventListener('click', function () {
    let villeALivrer = document.querySelectorAll('.villeAlivrer');
    if(villeALivrer.length == 0){
        fetch(window.location.origin + '/api/rest/tournee/addListTournee', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(allTournee)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erreur lors de la requête');
                }
                return response;
            })
            .then(data => {
                console.log('Réponse du serveur:', data);
                document.getElementById("info-retour").classList.remove("undisplayed")
                document.getElementById("messageContainer").classList.add("undisplayed");
            })
            .catch(error => {
                console.error('Erreur:', error);
            });
    } else {
        document.getElementById("messageContainer").classList.remove("undisplayed");
    }


})

getCitiesAndSave();

document.getElementById("pagePrecedente").addEventListener("click", function () {
    window.location.href = "tournee1.html"
})