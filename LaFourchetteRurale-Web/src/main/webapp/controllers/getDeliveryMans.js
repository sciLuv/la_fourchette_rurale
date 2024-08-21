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


// 获取快递员数据并保存到 localStorage
function getDeliveryMansAndSave() {
    const requestOption = {
        headers: { 'Content-Type': 'application/json' },
    };
    fetch(window.location.origin + "/api/rest/deliveryMan/allDeliveryMans", requestOption)
        .then(response => response.json())
        .then(deliveryMans => {
            if (deliveryMans && deliveryMans.length > 0) {
                saveToLocalStorage("deliveryMans", deliveryMans);

                // 更新界面显示
                displayDeliveryMans(deliveryMans);
            } else {
                incorrectLoginDisplay();
            }
        })
        .catch(error => {
            console.log("Une erreur s'est produite lors de la récupération des livreurs:", error);
        });
}

// 从 localStorage 获取快递员数据并显示在界面上
function displayDeliveryMansFromLocalStorage() {
    const deliveryMans = getFromLocalStorage("deliveryMans");
    if (deliveryMans && deliveryMans.length > 0) {
        displayDeliveryMans(deliveryMans);
    } else {
        console.log("erreur");
    }
}

// 将快递员数据显示在下拉菜单中
function displayDeliveryMans(deliveryMans) {
    const deliveryManSelect = document.getElementById("deliveryMan");
    if (deliveryManSelect) {
        // 清空现有的选项
        deliveryManSelect.innerHTML = "";
        deliveryMans.forEach(deliveryMan => {
            const optionElement = document.createElement('option');
            optionElement.value = deliveryMan.deliveryManId; // 假设数据库中每个快递员都有一个唯一的ID
            optionElement.textContent = deliveryMan.firstName;
            deliveryManSelect.appendChild(optionElement);
        });
    }
}

// 页面加载时尝试从 localStorage 获取数据并显示
displayDeliveryMansFromLocalStorage();

// 在页面加载时尝试从数据库获取数据并更新 localStorage 中的数据
getDeliveryMansAndSave();
