import {findDietNameOfMemberByDietId, generateADayList} from "../view/memberMealSelection.js"
import {getNowDate} from "./datesOperations.js"


// Création des éléments HTML
export function addMenuToSelection(){
    let menuInfos = JSON.parse(localStorage.getItem("menus"));
    let memberDiet = findDietNameOfMemberByDietId()

    for(let i = 0; i < menuInfos.length; i++ ){
            let main = document.querySelector('.day-number-' +  (menuInfos[i].dayPosition-1) + " .meal-"+ menuInfos[i].mealTypeId); 
            console.log(i);

            let mealPlace
            if(menuInfos[i].mealTypeId == 1){
                mealPlace = ((menuInfos[i].dayPosition-1)*2)
            } else {
                mealPlace = (((menuInfos[i].dayPosition-1)*2)+1)
            }

            //l'element de menu dans son ensemble
            const menuContainer = document.createElement('div');
            menuContainer.classList.add('member-menu-selection');
            menuContainer.classList.add('container');
            console.log(menuInfos[i].menu.dietType);
            console.log(memberDiet);
            if((menuInfos[i].menu.dietType != memberDiet)||(document.getElementById("diet_type").hasAttribute("checked"))){
                menuContainer.classList.add('undisplayed');
                menuContainer.setAttribute("notdefaultdiet", "")
                menuContainer.setAttribute("mealPlace", mealPlace)
            }
            //l'element qui contient le titre du menu et sa checkbox
            const menuTitleContainer = document.createElement('div');
            menuTitleContainer.classList.add("menu-title-container");
    
            const titleMenu = document.createElement('h3');
            titleMenu.textContent = menuInfos[i].menu.name;
    

            const allTheMenuCheckBox = document.createElement('input');
            allTheMenuCheckBox.setAttribute("mealPlace", mealPlace)
            allTheMenuCheckBox.setAttribute("menuCheckBox", "")
            allTheMenuCheckBox.setAttribute("menu", "")
            if((menuInfos[i].menu.dietType != memberDiet)||(document.getElementById("diet_type").hasAttribute("checked"))){
                allTheMenuCheckBox.setAttribute("notdefaultdiet", "")
            }
                // Récupérer les informations sur les menus, le régime du membre et les dates de livraison

            // Parcourir les menus pour sélectionner le menu par défaut correspondant au régime du membre
            
            console.log(menuInfos[i].menu.dietType + " " + memberDiet + " " + menuInfos[i].defaultMenu);
            if((menuInfos[i].menu.dietType == memberDiet)&&(menuInfos[i].defaultMenu)){
                            allTheMenuCheckBox.setAttribute("userdefaultmenu", "")
            }
        
            allTheMenuCheckBox.type = 'checkbox'
            allTheMenuCheckBox.id = "menu-" + menuInfos[i].menu.menuId

            allTheMenuCheckBox.addEventListener("click", function(){
                

                let mealPlace
                if(menuInfos[i].mealTypeId == 1){
                    mealPlace = ((menuInfos[i].dayPosition-1)*2)
                } else {
                    mealPlace = (((menuInfos[i].dayPosition-1)*2)+1)
                }

                console.log(`input[type="checkbox"][id="${mealPlace}"]`);
                let allCheckBoxWithSameMealPlace = document.querySelectorAll(`input[type="checkbox"][mealPlace="${mealPlace}"]`)
                console.log(allCheckBoxWithSameMealPlace);
                let json = JSON.parse(localStorage.getItem("selectionMeal"))


                if(allTheMenuCheckBox.hasAttribute("checked")){
                    allTheMenuCheckBox.removeAttribute("checked");
                    delete json[mealPlace].choiceDate
                    delete json[mealPlace].dishId1
                    delete json[mealPlace].dishId2
                    delete json[mealPlace].dishId3
                    
                } else {
                    allCheckBoxWithSameMealPlace.forEach(checkBox =>{
                        console.log(checkBox);
                        checkBox.removeAttribute("checked")
                        checkBox.checked = false; 
                    })
                    document.getElementById("default_menu").checked = false;
                    document.getElementById("default_menu").removeAttribute("checked");
                    allTheMenuCheckBox.setAttribute("checked", ""); 
                    allTheMenuCheckBox.checked = true
                    json[mealPlace].choiceDate = getNowDate()
                    json[mealPlace].dishId1 = menuInfos[i].menu.starter.dishId,
                    json[mealPlace].dishId2 = menuInfos[i].menu.mainCourse.dishId
                    json[mealPlace].dishId3 = menuInfos[i].menu.dessert.dishId
                }
                localStorage.setItem("selectionMeal", JSON.stringify(json))
            })
            
            

            if(!(menuInfos[i].menu.dietType == "defaut")){
                const h1SpanContainer = document.createElement("div")
                h1SpanContainer.classList.add("h1-span-menu-container")
                const badgeDiet = document.createElement("span")
                badgeDiet.classList.add("badge")
                badgeDiet.classList.add("text-bg-light")
                badgeDiet.innerHTML = menuInfos[i].menu.dietType
                h1SpanContainer.append(titleMenu)
                h1SpanContainer.append(badgeDiet)
                menuTitleContainer.append(h1SpanContainer)
            } else {
                menuTitleContainer.append(titleMenu)
            }
            menuTitleContainer.append(allTheMenuCheckBox)
    
            const allDishContainer = document.createElement('div');
            allDishContainer.classList.add("container")
            allDishContainer.classList.add("pb-2")
            //l'element contenant l'ensemble des information lié à l'entrée
            const starterContainer = document.createElement('div');
            starterContainer.classList.add("container");
            starterContainer.classList.add("ms-5")
    
            const starterTitle = document.createElement('h4');
            starterTitle.textContent = menuInfos[i].menu.starter.dishName
    
            const starterImg = document.createElement('img')
    
            const starterDescription  = document.createElement('p')
            starterDescription.classList.add("ms-2")
            starterDescription.textContent = menuInfos[i].menu.starter.description
            
            const starterAllergen = document.createElement('p')
    
            starterContainer.appendChild(starterTitle)
            starterContainer.appendChild(starterDescription)
            
            console.log(starterContainer);
            //l'element contenant l'ensemble des information lié au plat principal
            const mainCourseContainer = document.createElement('div');
            mainCourseContainer.classList.add("container");
            mainCourseContainer.classList.add("ms-5")
    
            const mainCourseTitle = document.createElement('h4');
            mainCourseTitle.textContent = menuInfos[i].menu.mainCourse.dishName
    
            const mainCourseImg = document.createElement('img')
    
            const mainCourseDescription  = document.createElement('p')
            mainCourseDescription.classList.add("ms-2")
            mainCourseDescription.textContent = menuInfos[i].menu.mainCourse.description
            
            console.log(menuInfos[i].menu.mainCourse.description);
            const mainCourseAllergen = document.createElement('p')
    
            mainCourseContainer.appendChild(mainCourseTitle)
            mainCourseContainer.appendChild(mainCourseDescription)
            
            //l'element contenant l'ensemble des information lié au plat principal
            const dessertContainer = document.createElement('div');
            dessertContainer.classList.add("container");
            dessertContainer.classList.add("ms-5")
    
            const dessertTitle = document.createElement('h4');
            dessertTitle.textContent = menuInfos[i].menu.dessert.dishName
    
            const dessertImg = document.createElement('img')
    
            const dessertDescription  = document.createElement('p')
            dessertDescription.classList.add("ms-2")
            dessertDescription.textContent = menuInfos[i].menu.dessert.description
            
            console.log(menuInfos[i].menu.dessert.description);
            
            const dessertAllergen = document.createElement('p')
    
            dessertContainer.appendChild(dessertTitle)
            dessertContainer.appendChild(dessertDescription)
            
            
            // Ajout des éléments dans la structure principale
            menuContainer.appendChild(menuTitleContainer);

            allDishContainer.appendChild(starterContainer);
            allDishContainer.appendChild(mainCourseContainer);
            allDishContainer.appendChild(dessertContainer);

            menuContainer.appendChild(allDishContainer);
            
            console.log(mainCourseContainer);
            console.log(starterContainer);
            console.log(dessertContainer)
    
            // Ajout de la structure dans le document
            main.appendChild(menuContainer);
    }
}

export function generateDay(date, dayNumber) {
    // Create the main container element
    const dayContainer = document.createElement('div');
    dayContainer.classList.add('day');
    dayContainer.classList.add("day-number-" + dayNumber)
    dayContainer.classList.add('container');
    // Create the date element and set its content
    const dateElement = document.createElement('h3');
    dateElement.classList.add('date-day');
    dateElement.classList.add('border-bottom'); 
    dateElement.classList.add('border-success'); 
    dateElement.classList.add('border-2');
    dateElement.textContent = date; // Assuming `formatDate` function exists for flexible date formatting
  
    // Create the meal containers and headings
    const lunchContainer = document.createElement('div');
    lunchContainer.classList.add('meal');
    lunchContainer.classList.add('meal-1');
    lunchContainer.classList.add('container');
    const lunchHeading = document.createElement('h4');
    lunchHeading.classList.add("ms-2");
    lunchHeading.textContent = 'Déjeuner';
    lunchContainer.appendChild(lunchHeading);
  
    const dinnerContainer = document.createElement('div');
    dinnerContainer.classList.add('meal');
    dinnerContainer.classList.add('meal-2')
    dinnerContainer.classList.add('container');
    const dinnerHeading = document.createElement('h4');
    dinnerHeading.textContent = 'Diner';
    dinnerContainer.appendChild(dinnerHeading);
  
    // Append all elements to the main container
    dayContainer.appendChild(dateElement);
    dayContainer.appendChild(lunchContainer);
    dayContainer.appendChild(dinnerContainer);
  
    // Use document.body or an appropriate target element for appending
    document.querySelector("main").appendChild(dayContainer);
  }
  

  
