  import {getNowDate, getNextMonday, formattedDate, getNextDay, generateDateList, generateDateListAlternate, generateDateListAlternate2, calculerDatefacture, ajouterJours} from "../scripts/datesOperations.js"
import getDays from "../controllers/dayController.js"

/* 
getDays()*/
let days = JSON.parse(localStorage.getItem("days"))

let date = getNowDate()
console.log(date);
let tournee

Promise.all([getDays()])
  .then(() => {
    days = JSON.parse(localStorage.getItem("days"));


    fetch( window.location.origin + '/api/rest/tourneeR/byDate/' + date )
  .then(response => {
    if (!response.ok) {
      throw new Error('Erreur lors de la requête.');
    }
    console.log(response.json);
    return response.json();
  })
  .then(data => {
    tournee = data

    function getDatesForDayOfWeek(startDateStr, dayName) {
        const startDate = new Date(startDateStr);
        const dayOfWeek = ['dimanche', 'lundi', 'mardi', 'mercredi', 'jeudi', 'vendredi', 'samedi'].indexOf(dayName.toLowerCase());
        if (dayOfWeek === -1) {
            console.error('Nom de jour invalide.');
            return [];
        }
    
        const result = [];
        let currentDate = new Date(startDate);
        currentDate.setDate(currentDate.getDate() + (dayOfWeek - currentDate.getDay() + 7) % 7); // Trouver la prochaine occurrence du jour de la semaine
        for (let i = 0; i < 21; i++) { // Parcourir les trois prochaines semaines (21 jours)
            result.push(new Date(currentDate));
            currentDate.setDate(currentDate.getDate() + 7); // Avancer d'une semaine
        }
        return result;
    }
    
    let namesOfDates = []
    
    days.forEach(element => {
        namesOfDates.push(element.nameOfDay)
    });
    console.log(namesOfDates);
    let dates = []
    
    namesOfDates.forEach(element => {
        const dates1 = getDatesForDayOfWeek(date, element);
        dates1.forEach(date => {
            dates.push(date.toISOString().split('T')[0]);
        });
    });
    console.log(dates);
    const datesObjets = dates.map(dateStr => new Date(dateStr));
    
    // Trier les dates dans l'ordre croissant
    datesObjets.sort((a, b) => a - b);
    
    // Convertir les objets Date en chaînes de dates au format "YYYY-MM-DD"
    const datesTrie = datesObjets.map(dateObj => dateObj.toISOString().split('T')[0]);

    function filtrerDatesDupliquees(tournee, dates) {
        // Créer un ensemble pour stocker les dates uniques du tableau d'objets
        const datesTournee = new Set(tournee.map(obj => obj.date));

        // Filtrer les dates dupliquées dans le tableau de chaînes
        const datesFiltrees = dates.filter(date => !datesTournee.has(date));

        return datesFiltrees;
    }

    /*  */
    const datesFiltrees = filtrerDatesDupliquees(tournee, datesTrie);
    console.log(datesFiltrees);
    const selectElement = document.getElementById("Select-delivery-date");
    
    const datesLimitees = datesFiltrees.slice(0, 35);
    
    datesFiltrees.forEach(date => {

        const dateObj = new Date(date);
        const optionElement = document.createElement('option');
        const jour = dateObj.getDate();
        const mois = dateObj.getMonth() + 1;
        const annee = dateObj.getFullYear();
        const nomJour = ['dimanche', 'lundi', 'mardi', 'mercredi', 'jeudi', 'vendredi', 'samedi'][dateObj.getDay()];
        optionElement.value = date;
        console.log("nom" + nomJour)
        console.log(dateObj.getDay())
        if((nomJour != "dimanche")&&(nomJour != 'mardi')&&(nomJour != 'jeudi')&&(nomJour != 'samedi')){
        optionElement.textContent = `${nomJour} ${jour < 10 ? '0' + jour : jour}/${mois < 10 ? '0' + mois : mois}/${annee}`;
        selectElement.appendChild(optionElement);
         }


    });

  })
  .catch(error => {
    console.error('Erreur:', error);
  });

    console.log("Toutes les informations ont été stockées dans le localStorage.");
  })
  .catch(error => {
    console.error("Une erreur s'est produite :", error);
  });






  document.getElementById("nextPage").addEventListener("click", function(){
    localStorage.setItem("dateForTournee", document.getElementById("Select-delivery-date").value)
    window.location.href = "tournee2.html"
})
