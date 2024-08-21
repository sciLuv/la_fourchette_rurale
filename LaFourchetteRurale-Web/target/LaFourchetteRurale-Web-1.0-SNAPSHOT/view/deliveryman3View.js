let deliveryInfos = JSON.parse(localStorage.getItem("memberInfosDeliveryEnd"));

let otherinfos = JSON.parse(localStorage.getItem("adherentDeliveryInfo"));

function formatPhoneNumber(phoneNumber) {
    // Supprimer tous les caractères non numériques du numéro de téléphone
    const cleaned = ('' + phoneNumber).replace(/\D/g, '');
    
    // Ajouter '0' devant le premier chiffre
    const firstDigit = cleaned.charAt(0);
    const restDigits = cleaned.substring(1);
    const formatted = '0' + firstDigit + restDigits;
    
    // Diviser le reste des chiffres en groupes de deux chiffres
    const chunks = formatted.match(/.{1,2}/g);
    
    // Concaténer les groupes avec des tirets
    const result = chunks.join('-');
    
    return result;
}

document.getElementById("adress").innerHTML = deliveryInfos.street + ", " + otherinfos.town;
document.getElementById("phone").innerHTML = formatPhoneNumber(deliveryInfos.phone);
document.getElementById("adhName").innerHTML = deliveryInfos.firstname + " " + deliveryInfos.lastname;
document.getElementById("other").innerHTML = deliveryInfos.deliveryInformation;

const checkboxNonLivraison = document.querySelectorAll('.checkboxNonLivraison');


let liv = document.getElementById("liv")
let noliv = document.getElementById("noLiv")
let nolivReason = document.getElementById("noLivReason")

liv.addEventListener("click", function(){
    if (liv.checked) {
        noliv.checked = false;
        nolivReason.classList.add("little-opacity")
        checkboxNonLivraison.forEach(element =>{
            element.checked = false;
        })
    } else {
        console.log("c'est pas checked");
    }
})



noliv.addEventListener("click", function(){
    if (noliv.checked) {
        liv.checked = false;
        nolivReason.classList.remove("little-opacity")
    } else {
        console.log("c'est pas checked");
        nolivReason.classList.add("little-opacity")
        checkboxNonLivraison.forEach(element =>{
            element.checked = false;
        })
    }
})




checkboxNonLivraison.forEach(function(checkbox) {
    checkbox.addEventListener('change', function() {
        if (checkbox.checked) {
            checkboxNonLivraison.forEach(function(otherCheckbox) {
                if (otherCheckbox !== checkbox) {
                    otherCheckbox.checked = false;
                }
            });
        }
    });
});


const sendInfoButton = document.getElementById('sendInfo');

sendInfoButton.addEventListener('click', function() {
    if((noliv.checked)||(liv.checked)){
        const checkedCheckboxes = document.querySelectorAll('.checkboxNonLivraison:checked');
        const selectedIds = [];
        checkedCheckboxes.forEach(function(checkbox) {
            selectedIds.push(Number(checkbox.getAttribute('idEchec')));
        });
    
        let valueToUse = 0;
    
        if (selectedIds.length > 0) {
            valueToUse = selectedIds[0];
        }
        if((noliv.checked)&&(valueToUse == 0)){
            document.getElementById("message").innerHTML = "Si le colis n'a pas pu être livré, veuillez sélectionner une raison."
        } else {
            let adherentDeliveryInfo = JSON.parse(localStorage.getItem("adherentDeliveryInfo"))
            let idPackage = adherentDeliveryInfo.idPackage
        
            fetch(window.location.origin + `/api/rest/deliveryR/endDelivery/${valueToUse}/${idPackage}`, {
                method: 'POST'
            })
            .then(response => {
                if (response == "false") {
                    document.getElementById("message").innerHTML = "Il y a eu un problème, veuillez recommencer."
                } else {
                    localStorage.removeItem("adherentDeliveryInfo")
                    localStorage.removeItem("memberInfosDeliveryEnd")

                    window.location.href = "deliveryman2.html"
                }
            })
            .catch(error => {
                console.error('There was a problem with your fetch operation:', error);
            });
        }
    
        console.log('ID des échecs sélectionnés :', selectedIds);
    } else {
        document.getElementById("message").innerHTML = "Vous n'avez pas sélectionné d'état pour la livraison."
    }
});