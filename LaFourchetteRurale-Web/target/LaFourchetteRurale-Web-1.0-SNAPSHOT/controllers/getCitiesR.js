// 将数据存储到 localStorage
function saveToLocalStorage(key, data) {
    localStorage.setItem(key, JSON.stringify(data));
}

// 从 localStorage 获取数据
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
                // 更新界面显示
                displayCities(cities);
                // 清空容器2中的内容
                   const villesAssociees = document.querySelector('.container-tournee2 .table');
                   villesAssociees.innerHTML = "";

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


export default function getCitiesAndSave2() {
    return new Promise((resolve, reject) => {
        const requestOption = {
            headers: { 'Content-Type': 'application/json' },
        };
        fetch(window.location.origin + "/api/rest/villes/allCities", requestOption)
            .then(response => response.json())
            .then(cities => {
                if (cities && cities.length > 0) {
                    saveToLocalStorage("cities", cities);
                    resolve(cities); // Résoudre la promesse avec les villes récupérées
                } else {
                    reject("Aucune ville n'a été récupérée."); // Rejeter la promesse en cas d'erreur
                }
            })
            .catch(error => {
                reject(error); // Rejeter la promesse en cas d'erreur
            });
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
                <div class="table-line-container villeAlivrer">
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
                const villesALivrer = document.querySelector('.container-tournee1 .table');
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
        const cityContainer = this.parentElement;
        const cityName = cityContainer.querySelector('.table-text[name="ville"]').textContent;

        // 创建新的城市元素并添加到 "villes associées a la tournée" 区域
        const newCityElement = document.createElement('div');
        newCityElement.classList.add('table-line-container', 'city-container', 'ass');
        newCityElement.innerHTML = `
            <span class="table-text ass">${cityName}</span>
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
            const cityName = cityContainer.querySelector('.table-text.ass').textContent;

            // 创建新的城市元素并添加到 "villes à livrer" 区域
            const newCityElement = document.createElement('div');
            newCityElement.classList.add('table-line-container', 'villeAlivrer');
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
        });
    });
}

