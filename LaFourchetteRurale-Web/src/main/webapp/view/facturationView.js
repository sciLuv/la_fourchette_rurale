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

let cp = document.getElementById("codePostal")
let ville = document.getElementById("ville")
let rue = document.getElementById("rue")
let numeroRue = document.getElementById("numeroRue")

document.getElementById("facturationForm").addEventListener("submit", function(event){
    event.preventDefault();
    if (cp.value === '' || ville.value === '' || rue.value === '' || numeroRue.value === '') {
        // Affiche une alerte si un champ est vide
        alert('Veuillez remplir tous les champs.');
        return; // Empêche la suite du code d'être exécutée
    } else{
        member.billAdress = cp.value +" "+ ville.value  +" "+  rue.value  +" "+ numeroRue.value
        localStorage.setItem("constructMember", JSON.stringify(member))

        window.location.href = "choix.html"
    }
    
    
    return false;
})


document.getElementById("prevPageButton").addEventListener("click", function(){
    window.location.href = "livraison.html"
})