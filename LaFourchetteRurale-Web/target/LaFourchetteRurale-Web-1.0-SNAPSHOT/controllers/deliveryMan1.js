let deliveryManID = 1


// 将数据转成字符串存储到 localStorage
function saveToLocalStorage(key, data) {
    localStorage.setItem(key, JSON.stringify(data));
}

// 从 localStorage 获取数据 (对象)
function getFromLocalStorage(key) {
    const data = localStorage.getItem(key);
    return JSON.parse(data);
}

function getTourneeAndSave() {
    const requestOption = {
        headers: { 'Content-Type': 'application/json' },
    };
    fetch(window.location.origin + "/api/rest/tournee/byDeliveryManID/" + deliveryManID, requestOption)
        .then(response => response.json())
        .then(tournees => {
            if (tournees && tournees.length > 0) {
                console.log(tournees)
                saveToLocalStorage("tourneesByDeliverman", tournees);
                //更新界面显示
                displayTournees(tournees);

            } else {
                incorrectLoginDisplay();
            }
        })
        .catch(error => {
            console.log("Une erreur s'est produite lors de la récupération des tournées", error);
        });
}

//从localStorage 获取快递员数据并显示在界面上
function displayTourneesFromLocalStorage() {
    let tourneesArray = getFromLocalStorage("tournees");
    if (tourneesArray && tourneesArray.length > 0) {
        displayTournees(tournees);

    } else {
        console.log("erreur");

    }

}
    function displayTournees(tournees) {
        const tourneesContainer = document.getElementById("tourneeInfo");
        if (tourneesContainer) {
            tourneesContainer.innerHTML = "";
            tournees.forEach(tournee => {
                tourneesContainer.innerHTML += `
                <div class="table-line-container">
                    <span class="table-text">tournée ${tournee.tourneeId}</span>
                    <span class="table-text">${tournee.date}</span>
                    <div class="flex">
                        <button class="little-btn btn btn-success bg-success btnToGoToNextPage" tournee=${tournee.tourneeId} date=${tournee.date}> <span>Sélectionner la tournée</span> </button>
                    </div>
                    </div>
                  `;
            });

        }

        let btnsToGoToNext = document.querySelectorAll(".btnToGoToNextPage")
        btnsToGoToNext.forEach( element =>{
        console.log(element)
            element.addEventListener("click", function(){
             console.log(element.getAttribute("tournee"))

                 const requestOption = {
                     headers: { 'Content-Type': 'application/json' },
                 };
                 fetch(window.location.origin + "/api/rest/tournee/getTownByTournee/" + element.getAttribute("tournee"), requestOption)
                     .then(response => response.json())
                     .then(towns => {
                     console.log(towns)
                         if (towns && towns.length > 0) {
                             console.log(towns)
                             saveToLocalStorage("townsByTournee", towns);
                             localStorage.setItem("tourneeAndDate", JSON.stringify({
                             tourneeId : element.getAttribute("tournee"),
                             date : element.getAttribute("date")
                             }))

                             window.location.href = "deliveryman2.html"

                         } else {
                             incorrectLoginDisplay();
                         }
                     })
                     .catch(error => {
                         console.log("Une erreur s'est produite lors de la récupération des tournées", error);
                     });

            })
        })

    }


getTourneeAndSave()
