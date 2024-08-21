import addMenuToSelection from "./addMenuElement.js"


const backUrlMenuSelection = window.location.origin + "/api/rest/order/MenuOpenSelection/1";
const backUrlMenus =  window.location.origin + "/api/rest/order/display/menu";
const backUrlDishes =  window.location.origin + "/api/rest/order/display/dishes";

function getOrderAndMenu(){

    const requestOption =  {
        headers: { 'Content-Type': 'application/json' },
    };

    fetch(backUrlMenuSelection, requestOption)
    .then(response => response.text())
    .then(text => {
        if(text !== ""){
            localStorage.setItem("menuOpenSelection", text);
            const jsonMenuOpen = JSON.parse(text);
            let menuIds = [];
            for(let i=0; i<jsonMenuOpen.length; i++){
                menuIds.push(jsonMenuOpen[i].menuId)
            }
            console.log(menuIds);
            console.log(JSON.stringify(menuIds));
            getMenuById(menuIds)
        } else {
            incorrectLoginDisplay()
        }
    })
}

function getMenuById(menuIds){
    const requestOption2 = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(menuIds)
    };
    fetch(backUrlMenus, requestOption2)
    .then(response => response.text())
    .then(text => {
        if (text !== "") {
            localStorage.setItem("menus", text);
            getDishesByMenuId(menuIds)
        } else {
            incorrectLoginDisplay();
        }
    });
}

function getDishesByMenuId(menuIds){
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
                            menus[i].starter = (dishes[0])
                            break;
                        case 1 :
                            menus[i].mainCourse = (dishes[0])
                            break;
                        case 2 :
                            menus[i].dessert = (dishes[0])
                            break;
                    }
                    dishes.shift()                                    
                }
            }
            
            localStorage.setItem("menus", JSON.stringify(menus));
            addMenuToSelection();
        } else {
            incorrectLoginDisplay();
        }
    });
}

getOrderAndMenu();