export function emptyFieldDisplay() {
    document.getElementById("messageLabel").innerText = "Vous devez indiquer un identifiant et/ou un mot de passe.";
    const iconBacks = document.querySelectorAll(".input-group-text.bg-black");
    Array.from(iconBacks).forEach(
        iconBack => iconBack.setAttribute("class","input-group-text bg-warning")
    );
    document.getElementById("connectionForm").reset();
}

export function incorrectUsernameDisplay() {
    document.getElementById("messageLabel").innerText = "Identifiant et/ou mot de passe incorrect(s).";
    const iconBacks = document.querySelectorAll(".input-group-text.bg-black");
    Array.from(iconBacks).forEach(
        iconBack => iconBack.setAttribute("class","input-group-text bg-warning")
    );
    document.getElementById("connectionForm").reset();
}