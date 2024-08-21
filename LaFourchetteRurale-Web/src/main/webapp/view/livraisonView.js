import getDays from "../controllers/dayController.js"
import getDiet from "../controllers/dietController.js"
import getAllFoodSpecificity from "../controllers/foodSpecificityController.js"
import getCitiesAndSave2 from "../controllers/getCitiesR.js"

getDays()
getDiet()
getAllFoodSpecificity()
getCitiesAndSave2()

let member = JSON.parse(localStorage.getItem("constructMember"))
let cities = JSON.parse(localStorage.getItem("cities"))
let diets = JSON.parse(localStorage.getItem("diets"))
let foods = JSON.parse(localStorage.getItem("foodSpecificity"))
let days = JSON.parse(localStorage.getItem("days"))


let ville = document.getElementById("ville")
for(let i = 0; i < cities.length; i++){
    console.log(cities[i]);
    ville.innerHTML +=
`<option value="${cities[i].id}">${cities[i].cityName}</option>`
}

let disponibiliteLivraison = document.getElementById("disponibiliteLivraison")
for(let i = 0; i < days.length; i++){
    console.log(days[i]);
    disponibiliteLivraison.innerHTML +=
`<option value="${days[i].id}">${days[i].nameOfDay}</option>`
}


document.getElementById("deliveryForm").addEventListener("submit", function(event){
    event.preventDefault()

    
    var selectedValues = [];

    // Récupère les jours de livraison sélectionnés
    for (var option of disponibiliteLivraison.selectedOptions) {
      selectedValues.push(Number(option.value));
    }
    
    // Récupère les autres informations du membre
    var town = ville.value;
    var street = document.getElementById("rueLivraison").value;
    var deliveryInformation = document.getElementById("informations").value;
    
    // Vérifie si une information est manquante
    if (town === '' || selectedValues.length === 0 || street === '' || deliveryInformation === '') {
      // Affiche une alerte si une information est manquante
      alert("Veuillez remplir tous les champs !");
    } else {
      // Si toutes les informations sont présentes, les assigne à l'objet member et affiche dans la console
      member.town = Number(town);
      member.deliveryDays = selectedValues;
      member.street = street;
      member.deliveryInformation = deliveryInformation;
      
      console.log(member.town + " " + member.deliveryDays + " " + member.street + " " + member.deliveryInformation);
      localStorage.setItem("constructMember", JSON.stringify(member))
      window.location.href = "facturation.html"
    }

    return false;
})

document.getElementById("prevPageButton").addEventListener("click", function(){
  window.location.href = "adhesion.html"
})