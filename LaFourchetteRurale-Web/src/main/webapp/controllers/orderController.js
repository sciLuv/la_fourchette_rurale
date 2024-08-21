import {addMenuToSelection, generateDay} from "../scripts/addMenuElement.js"
import {generateDayWithDate} from "../view/memberMealSelection.js"

const backUrlMenuSelection = window.location.origin + "/api/rest/order/MenuOpenSelection/";
const backUrlMenus = window.location.origin + "/api/rest/order/display/menu";
const backUrlDishes = window.location.origin + "/api/rest/order/display/dishes";


export function getOrderAndMenu(date) {
    console.log(date);

    const requestOption = {
        headers: { 'Content-Type': 'application/json' },
    };

    return new Promise((resolve, reject) => {
        fetch(backUrlMenuSelection + date, requestOption)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.text();
            })
            .then(text => {
                if (text !== "") {
                    localStorage.setItem("menus", text);
                    const jsonMenuOpen = JSON.parse(text);
                    let menuIds = [];
                    for (let i = 0; i < jsonMenuOpen.length; i++) {
                        menuIds.push(jsonMenuOpen[i].menuId)
                    }
                    console.log(menuIds);
                    console.log(JSON.stringify(menuIds));
                    resolve(getMenuById(menuIds, date)); // Résoudre la promesse avec la valeur retournée par getMenuById()
                } else {
                    reject(new Error("Aucun menu trouvé.")); // Rejeter la promesse en cas d'erreur
                }
            })
            .catch(error => {
                reject(error); // Rejeter la promesse en cas d'erreur
            });
    });
}



export function getMenuById(menuIds, date){
    const requestOption2 = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(menuIds)
    };
    fetch(backUrlMenus, requestOption2)
    .then(response => response.text())
    .then(text => {
        if (text !== "") {
            let allMenus = JSON.parse(text)
            let menus = JSON.parse (localStorage.getItem("menus"))
            for(let i=0; i<allMenus.length; i++){
                menus[i].menu = allMenus[i]
            }
            localStorage.setItem("menus", JSON.stringify(menus));
            getDishesByMenuId(menuIds, date)
        } else {
            incorrectLoginDisplay();
        }
    });
}

export function getDishesByMenuId(menuIds, date){
    const requestOption2 = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(menuIds)
    };
    fetch(backUrlDishes, requestOption2)
    .then(response => response.text())
    .then(text => {
        if (text !== "") {
            let menus = JSON.parse(localStorage.getItem("menus"))
            let dishes = JSON.parse(text)

            for(let i=0; i<menus.length; i++){
                console.log("test0");
                for(let j=0; j<3; j++){
                    switch(j){
                        case 0 :
                            menus[i].menu.starter = (dishes[0])
                            break;
                        case 1 :
                            menus[i].menu.mainCourse = (dishes[0])
                            break;
                        case 2 :
                            menus[i].menu.dessert = (dishes[0])
                            break;
                    }
                    dishes.shift()                                    
                }
            }
            
            localStorage.setItem("menus", JSON.stringify(menus));
            generateDayWithDate(date)

            addMenuToSelection();
        } else {
            incorrectLoginDisplay();
        }
    });
}

export function postNewOrder(){
    const member = JSON.parse(localStorage.getItem("member"));
    const deliveryDate = JSON.parse(localStorage.getItem("deliveryDate"))
    const selectionMeal = JSON.parse(localStorage.getItem("selectionMeal"));

    let json = {
        selectionMeals : JSON.parse(localStorage.getItem("selectionMeal")),
        deliveryDates : JSON.parse(localStorage.getItem("deliveryDate")),
    }

    console.log(json);
    
    const backUrlSendOrder =  window.location.origin +"/api/rest/order/send/" + member.id;
    
    console.log(selectionMeal);
    const requestOption = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(json)
    };
    fetch(backUrlSendOrder, requestOption)
    .then(response => response.text())
    .then(text => {
        if (text !== "") {
            document.querySelector(".order-container").innerHTML = 

            `
            <div class="d-flex align-items-center justify-content-center border-top">
            <div class='container m-5 p-4 text-success fw-bold fs-5 text-center'>Commande prise en compte !</div>
            </div>
            `
            console.log("c'est fonctionnel");
        } else {
            /* incorrectLoginDisplay(); */
        }
    });
}