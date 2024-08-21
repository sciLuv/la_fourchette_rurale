import getCitiesAndSave2 from "../controllers/getCitiesR.js";

// Appeler getCitiesAndSave2 et attendre la résolution de la promesse
getCitiesAndSave2().then(() => {
    // Une fois que la promesse est résolue, récupérer les villes du local storage
    let cities = JSON.parse(localStorage.getItem("cities"));

    // Ajouter un événement submit au formulaire
    document.getElementById('registerForm').addEventListener('submit', function(event){
        event.preventDefault();

        let isCPExist = false;
        // Parcourir les villes pour vérifier si le code postal existe
        cities.forEach(element => {
            if(element.cityCP == document.getElementById("verificationInput").value){
                isCPExist = true;
            }
        });

        if(isCPExist){
            // Rediriger vers la page "register.html" si le code postal existe
            window.location.href = "register.html";
        } else {
            // Afficher un message si le code postal n'existe pas
            document.getElementById("codePostalMessage").classList.remove("undisplayed");
        }
        // Retourner false pour empêcher la soumission du formulaire
        return false;
    });
});