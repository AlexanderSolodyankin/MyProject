const login = document.getElementById("login")
const password = document.getElementById("password")
const email = document.getElementById("email")
const btn = document.querySelector("button")

btn.onclick = function() {
    fetch("http://localhost:8080/users/registration", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            login: login.value,
            password: password.value,
            email: email.value,
        })
    }).then((res) => {
        return res.json()
    }).then(data => {
        localStorage.setItem("token", data)
    })
}

const token = localStorage.getItem("token");