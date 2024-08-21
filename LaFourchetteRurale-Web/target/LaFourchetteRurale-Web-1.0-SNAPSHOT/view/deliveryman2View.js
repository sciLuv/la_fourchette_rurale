let infoFromStorage = JSON.parse(localStorage.getItem("tourneeAndDate"))

let tourneeID = infoFromStorage.tourneeId
let TownID = JSON.parse(localStorage.getItem("townsByTournee"))
let date = infoFromStorage.date
let jsonInfo
let TownName 

fetch(window.location.origin + '/api/rest/deliveryR/towns', {
  method: 'POST',
  headers: {
      'Content-Type': 'application/json'
  },
  body: JSON.stringify(TownID)
})
.then(response => {
  if (!response.ok) {
      throw new Error('Network response was not ok');
  }
  return response.json();
})
.then(data => {
  // Traitez la réponse ici
  console.log(data);
  TownName = data
})
.catch(error => {
  // Gérez les erreurs ici
  console.error('There was a problem with your fetch operation:', error);
});


function formatDateJJMMAAAA(dateStr) {
  const date = new Date(dateStr);
  return date.toLocaleDateString('fr-FR');
}

const dateFormatee = formatDateJJMMAAAA(date);

document.getElementById("numTournee").innerHTML = tourneeID
document.getElementById("dateDisplay").innerHTML = dateFormatee

function generateAndInsertHTML(data, k) {
  let html = '';
  for (let i = 0; i < data.length; i++) {
      let disabledAttribute = "";
      // Si l'élément 'packageInfoForDisplay' est présent dans le localStorage, désactive le bouton
      if (!localStorage.getItem('packageInfoForDisplay')) {
          disabledAttribute = "disabled";
      }
      html += `
          <div class="table-line-container">
              <span class="table-text col1">colis ${data[i].idPackage}</span>
              <span class="table-text col2">${TownName[k] + ", " + data[i].street}</span>
              <span class="table-text col3">${data[i].lastName} ${data[i].firstname}</span>
              <div class="flex">
                  <button class="little-btn btn btn-success bg-success seeAdhbtn" town="${TownName[k]}" deliveryDate="${date}" idAdh="${data[i].idAdh}" idPackage="${data[i].idPackage}" ${disabledAttribute}> <span>Voir</span> </button>
              </div>
          </div>
      `;
  }
  // Insérer le HTML généré dans le DOM
  document.getElementById("tableDetailDelivery").innerHTML += html;
}

// Tableau pour stocker toutes les promesses des requêtes fetch
let fetchPromises = [];

for (let k = 0; k < TownID.length; k++) {
  // Créer une promesse pour chaque requête fetch et l'ajouter au tableau
  let promise = fetch(window.location.origin + `/api/rest/deliveryR/getPackage/${tourneeID}/${TownID[k]}/${date}`, {
      method: 'POST'
  })
      .then(response => {
          if (!response.ok) {
              throw new Error('Network response was not ok');
          }
          return response.json(); // ou response.text() selon le type de réponse attendue
      })
      .then(data => {
          // Traitez la réponse ici
          jsonInfo = data;
          console.log(jsonInfo);

          // Appeler la fonction pour générer et insérer le HTML dans le DOM
          generateAndInsertHTML(jsonInfo, k);
      })
      .catch(error => {
          // Attrapez les erreurs ici
          console.error('There was a problem with your fetch operation:', error);
      });

  // Ajouter la promesse au tableau
  fetchPromises.push(promise);
}

// Une fois que toutes les promesses sont résolues, exécutez le code suivant
Promise.all(fetchPromises)
  .then(() => {
      // Attacher les événements aux éléments générés après que le HTML ait été inséré dans le DOM
      let btns = document.querySelectorAll(".seeAdhbtn");
      if(btns.length == 0){
          document.getElementById("end-tournee-container").classList.remove("undisplayed")
      }else{
        btns.forEach(element => {
          element.addEventListener("click", function () {
              console.log("deliveryman2 add local storage");
              localStorage.setItem("adherentDeliveryInfo", JSON.stringify({
                  idAdh: element.getAttribute("idAdh"),
                  idPackage: element.getAttribute("idPackage"),
                  deliveryDate: element.getAttribute("deliveryDate"),
                  town: element.getAttribute("town")
              }));

              fetch(window.location.origin + '/api/rest/deliveryR/memberInfo/' + element.getAttribute("idAdh"))
                  .then(response => {
                      if (!response.ok) {
                          throw new Error('Erreur lors de la requête');
                      }
                      return response.json();
                  })
                  .then(data => {
                      console.log(data);
                      localStorage.removeItem("memberInfosDeliveryEnd");
                      localStorage.setItem("memberInfosDeliveryEnd", JSON.stringify(data));
                      // Redirection après la mise à jour du stockage local
                      window.location.href = "deliveryman3.html";
                  })
                  .catch(error => {
                      console.error('Une erreur s\'est produite:', error);
                  });
          });
      });
      }
  })
  .catch(error => {
      // Attrapez les erreurs ici
      console.error('There was a problem with your fetch operations:', error);
  });
  
document.getElementById("beginTournee").addEventListener("click", function(){

        // Créer l'objet JSON s'il n'existe pas
        const packageInfo = {
          tourneeId: tourneeID,
          date: date
      };
      // Enregistrer l'objet JSON dans le localStorage
      localStorage.setItem('packageInfoForDisplay', JSON.stringify(packageInfo));

    const currentDate = new Date();
    const year = currentDate.getFullYear();
    const month = (currentDate.getMonth() + 1).toString().padStart(2, '0'); // Les mois commencent à partir de 0
    const day = currentDate.getDate().toString().padStart(2, '0');
    const hours = currentDate.getHours().toString().padStart(2, '0');
    const minutes = currentDate.getMinutes().toString().padStart(2, '0');
    const seconds = currentDate.getSeconds().toString().padStart(2, '0');
    
    const formattedDate = `${year}-${month}-${day};`
    console.log(formattedDate);

    fetch(window.location.origin + `/api/rest/deliveryR/beginTournee/${tourneeID}`, {
      method: 'POST'
  })
  .then(response => {
      if (!response.ok) {
          throw new Error('Network response was not ok');
      }
      return response;
  })
  .then(() => {
      document.getElementById("beginTournee").classList.add("undisplayed");
      let btns = document.querySelectorAll(".seeAdhbtn");
  
      btns.forEach(element => {
          element.removeAttribute("disabled");
          element.addEventListener("click", function(){
              console.log("deliveryman2 add local storage");
              localStorage.setItem("adherentDeliveryInfo", JSON.stringify({
                  idAdh : element.getAttribute("idAdh"),
                  idPackage : element.getAttribute("idPackage"),
                  deliveryDate : element.getAttribute("deliveryDate"),
                  town : element.getAttribute("town")
              }));
  
              fetch(window.location.origin + '/api/rest/deliveryR/memberInfo/' + element.getAttribute("idAdh"))
              .then(response => {
                  if (!response.ok) {
                      throw new Error('Erreur lors de la requête');
                  }
                  return response.json();
              })
              .then(data => {
                  console.log(data);
                  localStorage.removeItem("memberInfosDeliveryEnd");
                  localStorage.setItem("memberInfosDeliveryEnd", JSON.stringify(data));
                  // Redirection après la mise à jour du stockage local
                  window.location.href = "deliveryman3.html";
              })
              .catch(error => {
                  console.error('Une erreur s\'est produite:', error);
              });
          });
      });
  })
  .catch(error => {
      console.error('There was a problem with your fetch operation:', error);
  });
  

})
  

if (!localStorage.getItem('packageInfoForDisplay')) {

} else {
  document.getElementById("beginTournee").classList.add("undisplayed")
}

document.getElementById("EndTournee").addEventListener("click", function(){
  fetch(window.location.origin + `/api/rest/deliveryR/endOfTournee/${tourneeID}`, {
    method: 'PUT',
    headers: {
        'Content-Type': 'application/json'
    },
})
.then(response => {
    if (!response.ok) {
        throw new Error('Network response was not ok');
    }
    return response;
})
.then(data => {
    console.log('Réponse du serveur :', data);
    localStorage.removeItem("packageInfoForDisplay")
    localStorage.removeItem("memberInfosDeliveryEnd")
    localStorage.removeItem("adherentDeliveryInfo")
    window.location.href="deliveryman1.html"
})
.catch(error => {
   document.getElementById("message").innerHTML = "Il y a eu une erreur."
});
})