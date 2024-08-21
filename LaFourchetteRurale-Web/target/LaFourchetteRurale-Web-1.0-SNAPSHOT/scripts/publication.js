/////////
////Gestion de l'affichage et du contrôle de la gestion des menus
/////////


//Mes constantes
const menuList = document.getElementById('menu-list');
const checkboxes = document.querySelectorAll("input[type=checkbox]");
const errorDiv = document.getElementById("error");
const divRecap = document.querySelector("#display-menu-choices")
const endDate = document.getElementById("end-date-picker");
const startDate = document.getElementById("start-date-picker");
const searchInput = document.querySelector('.search-input');
const btnValidateAll = document.getElementById("validate-all");
const btnValidateSelection = document.getElementById("validate-diet-meal");


// Affichage du jour
const dayPositionDisplayed = document.getElementById("day");
let j = 1;
dayPositionDisplayed.innerHTML = j;

//Affichage du type de repas
let mealType = [];
mealType = ["Déjeuner", "Dîner"];
let t = 0;
const mealTypeDisplayed = document.getElementById("meal-type");
mealTypeDisplayed.innerHTML = mealType[t];

//Affichage du régime
let dietsName = [];
const dietNameDisplayed = document.getElementById("diet-name");
let d = 0;
(async () => {
    dietsName = await getAvailableDiets(dietsName);
    dietNameDisplayed.innerHTML = dietsName[d];
})();

//Affichage des menus
(async () => {
    await getAvailableDiets([]);
    resetListDisplay();
    getAvailableMenusForSelectedDiet(dietsName, d)
})();

////Gestion des boutons de validation

//Bouton validation sélection et passage suivante
btnValidateSelection.addEventListener ('click',(event) => {
    event.preventDefault();
    clearErrDiv()
    if ((countVerifyCheckedCheckboxes() === true) && (countVerifyDefaultMenu() === true) && (checkDate() === true)) {
        sendToLocalStorage();
        resetRecapChoices();   
        changeConfigDisplay();
        resetListDisplay();
        //new
        searchInput.value = "";
        getAvailableMenusForSelectedDiet(dietsName, d);
    } else if (countVerifyCheckedCheckboxes() === false) {
        errorDiv.innerHTML = "Veuillez sélectionner au moins deux menus pour chaque régime alimentaire et type de repas."
    }else if (countVerifyDefaultMenu() === false) {
       errorDiv.innerHTML = "Veuillez sélectionner un menu par défaut.";
    } else if (checkDate () === false) {
        errorDiv.innerHTML = "Veuillez sélectionner les dates d'ouverture à la sélection.";
    }
});

function changeConfigDisplay(){
    t++;
    mealTypeDisplayed.innerHTML = mealType[t];
    if ((j == 7) && (d === dietsName.length-1) && (t === mealType.length-1)) {
        btnValidateAll.removeAttribute('hidden');
        btnValidateSelection.setAttribute('hidden', true);
        btnValidateAll.addEventListener('click',(event) => {
            if ((countVerifyCheckedCheckboxes() === true) && (countVerifyDefaultMenu() === true) && (checkDate() === true)) {
            event.preventDefault();
            sendToLocalStorage();
             for (let i = 0; i < localStorage.length; i++) {
                const key = localStorage.key(i);
                if (key.includes("menu")) {
                const collectedString = localStorage.getItem(key);
                let collectedObject = JSON.parse(collectedString);
                console.log(collectedObject);
                const requestOptions = {
                    method :'POST',
                    headers: {'Content-Type':'application/json'},
                    body: JSON.stringify(collectedObject)
                };
                fetch(window.location.origin + "/api/rest/admi/publication/register", requestOptions)
                console.log("coucou")
                }
             }
             localStorage.clear();
             const main = document.querySelector(".main-open-menus");
             main.innerHTML = "";
             main.innerHTML="Vos sélections ont bien été enregistrées.";

            }else if (countVerifyCheckedCheckboxes() === false) {
                errorDiv.innerHTML = "Veuillez sélectionner au moins deux menus pour chaque régime alimentaire et type de repas."
            } else if (countVerifyDefaultMenu() === false) {
                errorDiv.innerHTML = "Veuillez sélectionner un menu par défaut.";
            } else if (checkDate () === false) {
                errorDiv.innerHTML = "Veuillez sélectionner les dates d'ouverture à la sélection.";
            }
            });
            
        }

    if (t >= mealType.length) {
        t = 0;
        mealTypeDisplayed.innerHTML = mealType[t];
        d++;
        dietNameDisplayed.innerHTML = dietsName[d];
        if (d >= dietsName.length) {
            d = 0;
            dietNameDisplayed.innerHTML = dietsName[d];
            j++;
            dayPositionDisplayed.innerHTML = j;
        }
    }
}
////Fonctions

// Pour récupérer le nom des régimes
async function getAvailableDiets(dietsName) {
    const response = await fetch(window.location.origin + "/api/rest/diet/diets/valid");
    const text = await response.text();
    if (text !== "") {
      const diets = JSON.parse(text);
      diets.map(diet => dietsName.push(diet.name));
    }
    return dietsName;
}

//Pour récupérer les menus
async function getAvailableMenusForSelectedDiet(dietsName, d) {
    const displayMenu = document.getElementById('display-menus-db')
    fetch(window.location.origin + "/api/rest/admi/publication/menus/" + dietsName[d])
        .then(response => response.text())
        .then(text => {
            if (text !== "") {
                menus = JSON.parse(text);
                resetListDisplay()                
                createList(menus);
            }
        });
}

//Créer la liste de menus
async function createList(menus) {
resetListDisplay()
    menus.forEach(menu => {
        const li = document.createElement('li');
        const input = document.createElement('input');
        input.type = "checkbox";
        input.className = menu.menuId
        input.value = menu.name;
        const label = document.createElement('label');
        label.textContent = menu.name;
        li.appendChild(label);
        label.appendChild(input);
        menuList.appendChild(li);
    })

    // Filtrer les options en fonction de la saisie de l'utilisateur

   searchInput.addEventListener('keyup', () => {
         const filter = searchInput.value.toUpperCase();
         const optionsList = menuList.querySelectorAll('li');

         for (let i = 0; i < optionsList.length; i++) {
           const option = optionsList[i].textContent.toUpperCase();
           if (option.indexOf(filter) > -1) {
             optionsList[i].style.display = "";
           } else {
             optionsList[i].style.display = "none";
          }
         }
       });
    // Fin Filtre 
    displayChoices();
};

//Afficher les choix du user
function displayChoices () {

    for (let i = 0; i < document.querySelectorAll("input[type=checkbox]").length; i++) {
        document.querySelectorAll("input[type=checkbox]")[i].addEventListener ("change", ()  => {
        if (document.querySelectorAll("input[type=checkbox]")[i].checked == true ) {
            const spanRecap = document.createElement('span');
            spanRecap.setAttribute('id', document.querySelectorAll("input[type=checkbox]")[i].className);
            spanRecap.classList.add('chosen-menu');
            divRecap.appendChild(spanRecap);
            spanRecap.innerHTML = document.querySelectorAll("input[type=checkbox]")[i].value;

            // choisir au clic le menu par défaut
            spanRecap.addEventListener("click", function(){
                let chosenMenus = document.getElementsByClassName('chosen-menu');
                for (let j = 0; j < chosenMenus.length; j++) {
                    chosenMenus[j].classList.remove('chosenDefaultMenu'); 
                }
                spanRecap.classList.add("chosenDefaultMenu")
            })
            
             // Suppression du menu avec bouton et désactivation de la checkbox
             const deleteBtn = document.createElement('button');
                deleteBtn.classList.add('bouton-supprimer');
                deleteBtn.textContent = 'X';
                spanRecap.appendChild(deleteBtn);
             deleteBtn.addEventListener('click', () => {
                    divRecap.removeChild(spanRecap);
                    document.querySelectorAll("input[type=checkbox]")[i].checked =  false;
             });
        } else if (document.querySelectorAll("input[type=checkbox]")[i].checked == false) {
            const spanMenuSelected =
            document.querySelector(`span[id="${document.querySelectorAll("input[type=checkbox]")[i].className}"]`);
            divRecap.removeChild(spanMenuSelected);
        }
    });
    }
}

//Effacer la liste de menus
function resetListDisplay() {
    while (menuList.firstChild) {
        menuList.removeChild(menuList.firstChild);
    }
}

//Vérifier le nombre de checkbox checké
function countVerifyCheckedCheckboxes () {
    let areEnoughChecked;
    const checkedCheckboxes = document.querySelectorAll('input[type="checkbox"]:checked').length; 
    if (checkedCheckboxes < 2 ) {
        areEnoughChecked = false;
    } else {
        areEnoughChecked = true;
    }
        return areEnoughChecked;
}

//Vérifier le nombre de menu choisi
function countVerifyDefaultMenu() {
    const chosenDefaultMenu = document.querySelector(".chosenDefaultMenu");
    let isDefaultMenuChosen;
    if (!chosenDefaultMenu) {
        isDefaultMenuChosen = false;
    } else {
        isDefaultMenuChosen = true;
   }
    return isDefaultMenuChosen;
}
//Vérifier que les dates ont été choisies
function checkDate() {
    let isDateRegistered;
    
    if (startDate.value !== "") {
        isDateRegistered = true;
    } else {
        isDateRegistered = false;
    }
    return isDateRegistered;
    }

//Clear la div de message d'erreur
function clearErrDiv() {
    errorDiv.innerHTML = "";
}

// Enregistrer la sélection dans le localStorage
function sendToLocalStorage() {
    let spanRecap = document.querySelectorAll(".chosen-menu");
        for (let i = 0; i < spanRecap.length; i++) {
            let menuToOpen = {
                idMenuOpenSelection : -1,
                idTypeRepas : Number(t+1),
                idMenu : Number(spanRecap[i].id),
                selectionOpening : document.getElementById("start-date-picker").value,
                selectingClosing : document.getElementById("end-date-picker").value,
                isDefault : spanRecap[i].classList.contains("chosenDefaultMenu"),
                dayPosition : Number(j)
            }
            const jsonString = JSON.stringify(menuToOpen);
            localStorage.setItem("opened_menu" + "_" + i + "_" + mealType[t] + "_" + j + "_" + dietsName[d] , jsonString);
        }
}

//Reset le récap des choix
function resetRecapChoices () {
    divRecap.innerHTML = '';
    for (let i = 0; i < document.querySelectorAll("input[type=checkbox]").length; i++) {
    const spanMenuSelected = document.getElementById(document.querySelectorAll("input[type=checkbox]")[i].id);
       if (spanMenuSelected) {
           divRecap.removeChild(spanMenuSelected);
           document.querySelectorAll("input[type=checkbox]")[i].checked = false;
       }
    }
}

