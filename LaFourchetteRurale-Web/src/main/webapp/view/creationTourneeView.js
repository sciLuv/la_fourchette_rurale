//如果输入为空，背景色变成黄色
export function emptyFieldDisplay() {
    document.getElementById("messageLabel").innerText = "Vous devez choisir un livreurs et un/les villes.";
    const iconBacks = document.querySelectorAll(".input-group-text.bg-black");
    Array.from(iconBacks).forEach(
        iconBack => iconBack.setAttribute("class", "input-group-text bg-warning")
    );
    document.getElementById("creationTourneeForm").reset();
}
