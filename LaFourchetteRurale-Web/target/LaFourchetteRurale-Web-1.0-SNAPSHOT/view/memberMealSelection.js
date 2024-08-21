// Importation des fonctions et des contrôleurs nécessaires depuis d'autres fichiers
import {addMenuToSelection, generateDay} from "../scripts/addMenuElement.js"
import {getNowDate, getNextMonday, formattedDate, getNextDay, generateDateList, generateDateListAlternate, generateDateListAlternate2, calculerDatefacture} from "../scripts/datesOperations.js"
import  {getOrderAndMenu, getMenuById, getDishesByMenuId, postNewOrder} from "../controllers/orderController.js"
import getDays from "../controllers/dayController.js"
import getDiet from "../controllers/dietController.js"
import fetchDeliveryDatesForMember from "../controllers/daysforMember.js"

// Obtention de la date actuelle
const date = getNowDate();
let member = JSON.parse(localStorage.getItem("member"));
let days = JSON.parse(localStorage.getItem("days"));
let firstDayOfDelivery
let deliveryDate

// Appel des fonctions pour obtenir les jours de livraison, les informations sur le régime et la commande/menu pour la date donnée
Promise.all([getDays(), getDiet(), getOrderAndMenu(date), fetchDeliveryDatesForMember(member.id)])
  .then(() => {
    member = JSON.parse(localStorage.getItem("member"));
    days = JSON.parse(localStorage.getItem("days"));
    deliveryDate = JSON.parse(localStorage.getItem("dayOfDeliveryMember"));

    createSelectionMealJson()
    console.log("Toutes les informations ont été stockées dans le localStorage.");
  })
  .catch(error => {
    console.error("Une erreur s'est produite :", error);
  });



function createSelectionMealJson(){
    console.log(days);
    // Recherche du premier jour de livraison
    for (let i = 0; i < days.length; i++) {
        if(days[i].id == deliveryDate[0]){
            console.log("toussss");
            firstDayOfDelivery = days[i].nameOfDay
        }
    }
    console.log(firstDayOfDelivery);
    let test1 = getNextMonday(new Date(date));
    let test2 = getNextDay(firstDayOfDelivery,test1)
    let dateForJsonDate = generateDateListAlternate2(test2);
    let jsonDate = [
        {actualMealDate : dateForJsonDate[0]},
        {actualMealDate : dateForJsonDate[0]},
        {actualMealDate : dateForJsonDate[1]},
        {actualMealDate : dateForJsonDate[1]},
        {actualMealDate : dateForJsonDate[2]},
        {actualMealDate : dateForJsonDate[2]},
        {actualMealDate : dateForJsonDate[3]},
        {actualMealDate : dateForJsonDate[3]},
        {actualMealDate : dateForJsonDate[4]},
        {actualMealDate : dateForJsonDate[4]},
        {actualMealDate : dateForJsonDate[5]},
        {actualMealDate : dateForJsonDate[5]},
        {actualMealDate : dateForJsonDate[6]},
        {actualMealDate : dateForJsonDate[6]}
    ]
    localStorage.setItem("selectionMeal",JSON.stringify(jsonDate))
}


// Récupération des éléments de la page HTML
let checkboxMenu = document.getElementById("default_menu");checkboxMenu.removeAttribute("checked");
let checkBoxDiet = document.getElementById("diet_type");

// Ajout d'écouteurs d'événements aux cases à cocher pour activer ou désactiver certaines fonctionnalités
checkboxMenu.addEventListener("click", function(){
    if(checkboxMenu.hasAttribute("checked")){
        checkboxMenu.removeAttribute("checked");
        checkboxMenu.checked = false
        let allCheckBoxWithSameMealPlace = document.querySelectorAll(`input[type="checkbox"][userdefaultmenu]`)
        allCheckBoxWithSameMealPlace.forEach(checkBox =>{
            checkBox.removeAttribute("checked", "")
            checkBox.checked =false;
        })
        localStorage.removeItem("selectionMeal")
        createSelectionMealJson()
    } else {
        checkboxMenu.setAttribute("checked", "");
        checkboxMenu.checked = true
        let allCheckBoxWithSameMealPlace = document.querySelectorAll(`input[type="checkbox"][menu]`)
        allCheckBoxWithSameMealPlace.forEach(checkBox =>{
            checkBox.removeAttribute("checked", "")
            checkBox.checked =false;
        })
        defaultMenuSelection()
    }
})
checkBoxDiet.addEventListener("click", function(){
    let notDefaultDietContainer = document.querySelectorAll(`div[notdefaultdiet]`)
    let notDefaultDietInput = document.querySelectorAll(`input[notdefaultdiet][menuCheckBox]`)
    console.log(notDefaultDietInput);
    if(checkBoxDiet.hasAttribute("checked")){
        checkBoxDiet.removeAttribute("checked");
        notDefaultDietContainer.forEach(container =>{
            container.classList.add("undisplayed")
        })
        notDefaultDietInput.forEach(checkBox =>{
            if(checkBox.hasAttribute("checked")){
                let json = JSON.parse(localStorage.getItem("selectionMeal"))
                delete json[checkBox.getAttribute("mealPlace")].choiceDate
                delete json[checkBox.getAttribute("mealPlace")].dishId1
                delete json[checkBox.getAttribute("mealPlace")].dishId2
                delete json[checkBox.getAttribute("mealPlace")].dishId3
                localStorage.setItem("selectionMeal", JSON.stringify(json))
            }
            checkBox.removeAttribute("checked", "")
            checkBox.checked =false;
        })
    } else {
        checkBoxDiet.setAttribute("checked", "");
        notDefaultDietContainer.forEach(container =>{
            container.classList.remove("undisplayed")
        })
    }
})

// Ajout d'un écouteur d'événements au bouton de validation de la commande


// Générer les jours avec les dates correspondantes
export function generateDayWithDate(date){
    // Obtention des informations sur le membre et les jours de livraison
    let member = JSON.parse(localStorage.getItem("member"));
    let days = JSON.parse(localStorage.getItem("days"));
    let firstDayOfDelivery

    // Recherche du premier jour de livraison
    for (let i = 0; i < days.length; i++) {
        if(days[i].id == deliveryDate[0]){
            firstDayOfDelivery = days[i].nameOfDay
        }
    }

    // Génération des dates de livraison et affichage sur la page
    let json = []
    for(let i = 0; i < deliveryDate.length; i++) {
        for (let j = 0; j < days.length; j++) {
            if(days[j].id == deliveryDate[i]){
                json.push( formattedDate(getNextDay(days[j].nameOfDay, getNextMonday(date = new Date()))))
            }
        }
    }
    localStorage.setItem("deliveryDate", JSON.stringify(json))

    let test1 = getNextMonday(new Date(date));
    let test2 = getNextDay(firstDayOfDelivery,test1)
    let test3 = generateDateList(test2);
    console.log(test3);

    for(let i=0; i<test3.length; i++){
        console.log(test3[i]);
        generateDay(test3[i], i)
    }
}

// Générer une liste de jours alternée
export function generateADayList(date){
    let member = JSON.parse(localStorage.getItem("member"));
    let days = JSON.parse(localStorage.getItem("days"));
    let firstDayOfDelivery
    for (let i = 0; i < days.length; i++) {
        if(days[i].id == deliveryDate[0]){
            firstDayOfDelivery = days[i].nameOfDay
        }

    }

    let test1 = getNextMonday(new Date(date));
    let test2 = getNextDay(firstDayOfDelivery,test1)
    console.log(formattedDate(test2));
    let test3 = generateDateListAlternate(test2);
    return test3
}

// Trouver le nom du régime alimentaire du membre par son ID de régime
export function findDietNameOfMemberByDietId(){
    let dietList = JSON.parse(localStorage.getItem("diets"))
    let member = JSON.parse(localStorage.getItem("member"))
    let memberDiet
        for (let i = 0; i < dietList.length; i++) {
            if (dietList[i].dietId === member.idDiet) {
                memberDiet =  dietList[i].name;
            }
        }

    console.log(memberDiet)
    return memberDiet
}

// Générer la sélection des repas
function selectionMealJSONGeneration(){
    let json
    if(localStorage.getItem("selectionMeal")){
        json = JSON.parse(localStorage.getItem("selectionMeal"))
    } else {
        json = []
    }
}

// Sélectionner le menu par défaut en fonction du régime du membre
function defaultMenuSelection(){
    // Récupérer les informations sur les menus, le régime du membre et les dates de livraison
    let menuInfos = JSON.parse(localStorage.getItem("menus"));
    let memberDiet = findDietNameOfMemberByDietId()
    let dateList = generateADayList(date)
    console.log(dateList);
    let json = JSON.parse(localStorage.getItem("selectionMeal"))
    // Parcourir les menus pour sélectionner le menu par défaut correspondant au régime du membre

    for (let i = 0; i < menuInfos.length; i++) {
        if((menuInfos[i].menu.dietType == memberDiet)&&(menuInfos[i].defaultMenu)){
            for (let j = 1; j < 8; j++) {
                for (let k = 1; k < 3; k++) {
                    if((menuInfos[i].dayPosition == j)&&(menuInfos[i].mealTypeId == k)&&((((j-1)*2)+k)-1 < 14)){
                        console.log((((j-1)*2)+k)-1);
                        json[(((j-1)*2)+k)-1].dishId1 = menuInfos[i].menu.starter.dishId
                        json[(((j-1)*2)+k)-1].dishId2 = menuInfos[i].menu.mainCourse.dishId
                        json[(((j-1)*2)+k)-1].dishId3 = menuInfos[i].menu.dessert.dishId
                        json[(((j-1)*2)+k)-1].choiceDate = date
                        json[(((j-1)*2)+k)-1].actualMealDate = new Date().getFullYear() + "-" + dateList[j-1]
                    }
                }
            }
        }
    }
    let allCheckBoxWithSameMealPlace = document.querySelectorAll(`input[type="checkbox"][userdefaultmenu]`)
    console.log(allCheckBoxWithSameMealPlace);
    allCheckBoxWithSameMealPlace.forEach(checkBox =>{
        checkBox.setAttribute("checked", "")
        checkBox.checked = true;
    })
    localStorage.setItem("selectionMeal", JSON.stringify(json))
}

document.getElementById("validateOrder").addEventListener("click", function(){
    let json = JSON.parse(localStorage.getItem("selectionMeal"))
    let isJsonCompleted = true
    let isJsonCompletedArray = []
    json.forEach(part =>{
        if(part.choiceDate == undefined){
            isJsonCompleted = false
            isJsonCompletedArray.push(false)
        }
    })
    if(isJsonCompleted){
        json.forEach(part =>{
            part.choiceValidationDate = getNowDate()
            part.billDate = calculerDatefacture(part.actualMealDate)
        })
        localStorage.setItem("selectionMeal", JSON.stringify(json))
        postNewOrder()
    }
    else {
        document.getElementById("validateOrder").classList.add("undisplayed")
        document.getElementById("ordernotComplet").classList.remove("undisplayed")
        document.getElementById("ordernotComplet").classList.add("dflex")
        if(isJsonCompletedArray.length == 14){
            console.log("hohohohoho");
            document.getElementById("warning-order-text").innerHTML = "Vous n'avez sélectionnez aucun menu dans votre commande."
            document.getElementById("validateOrder2").classList.add("undisplayed")
        }
    }
})

document.getElementById("validateOrder2").addEventListener("click", function(){
    let json = JSON.parse(localStorage.getItem("selectionMeal"))
    json.forEach(part => {
        // Vérifier et attribuer des valeurs par défaut si les propriétés n'existent pas
        part.choiceDate = part.choiceDate || null;
        part.choiceValidationDate = part.choiceValidationDate || getNowDate();
        part.billDate = part.actualMealDate || calculerDatefacture(part.actualMealDate)
        part.dishId1 = part.dishId1 || null;
        part.dishId2 = part.dishId2 || null;
        part.dishId3 = part.dishId3 || null;
    });
    localStorage.setItem("selectionMeal", JSON.stringify(json))
    postNewOrder()
})
document.getElementById("returnToOrder").addEventListener("click", function(){
    window.scrollTo(0, 0)
    document.getElementById("validateOrder").classList.remove("undisplayed")
    document.getElementById("validateOrder").classList.add("dflex")
    document.getElementById("ordernotComplet").classList.add("undisplayed")
    document.getElementById("warning-order-text").innerHTML = "Vous n'avez pas sélectionnez de menu pour tout les repas de la semaine etes vous sur de vouloir valider votre commande ?"
    document.getElementById("validateOrder2").classList.remove("undisplayed")
})