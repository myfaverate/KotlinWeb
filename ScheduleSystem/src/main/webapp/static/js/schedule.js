window.onload = () => {
    checkUsername()
    checkPassword()
    checkConfirmPassword()
}

function checkUsername(){
    let username = document.getElementById("username")
    let usernameNote = document.getElementsByClassName("usernameNote")[0]
    console.log(`onload: usernameNode: ${usernameNote.innerText}`)
    username.addEventListener("input", () => {
        let usernameRegex = /^[\w\u3400-\u4dbf\u4e00-\u9fff]{5,10}$/
        if(usernameRegex.test(username.value.trim())){
            usernameNote.innerText = "OK"
            console.log("usernameRegex true")
        }else{
            usernameNote.innerText = "格式必须是5-10位数字或者字母"
            console.log("usernameRegex false")
        }
    })
    username.addEventListener("blur", () => {
        if (!username.value?.trim()){
            console.log("输入为空, 不发请求...")
            return
        }
        let xmlHttpRequest = new XMLHttpRequest()
        xmlHttpRequest.onreadystatechange = (e) => {
            console.log(`e: ${e}`)
            switch (xmlHttpRequest.readyState) {
                case 0: {
                    console.log(`result: 0`)
                    break
                }
                case 1: {
                    console.log(`result: 1`)
                    break
                }
                case 2: {
                    console.log(`result: 2`)
                    break
                }
                case 3: {
                    console.log("result: 3")
                    break
                }
                case 4: {
                    console.log("result 4")
                    console.log(`状态码: ${xmlHttpRequest.status}`)
                    if (xmlHttpRequest.status === 200){
                        console.log(`result: ${xmlHttpRequest.responseText}`)
                        const result = JSON.parse(xmlHttpRequest.responseText)
                        usernameNote.innerText = result.message
                    }
                    break
                }
                default: {
                    console.log("default...")
                }
            }
        }
        xmlHttpRequest.open("POST", "/user/checkUsernameUsed")
        xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
        console.log(`username innerText: ${username.innerText.trim()}`)
        console.log(`username textContent: ${username.textContent.trim()}`)
        console.log(`username value: ${username.value.trim()}`)
        xmlHttpRequest.send(`username=${username.value.trim()}`)
    })
}

function checkPassword(){
    let password = document.getElementById("password")
    let passwordNote = document.getElementsByClassName("passwordNote")[0]
    console.log(`onload: passwordNode: ${passwordNote.innerText}`)
    password.addEventListener("input", () => {
        let passwordRegex = /^\d{6}$/
        if(passwordRegex.test(password.value.trim())){
            passwordNote.innerText = "OK"
            console.log("passwordRegex true")
        }else{
            passwordNote.innerText = "格式必须是6位数字"
            console.log("passwordRegex false")
        }
    })
}
function checkForm(){
    let username = document.getElementById("username")
    let password = document.getElementById("password")
    let usernameRegex = /^[\w\u3400-\u4dbf\u4e00-\u9fff]{5,10}$/
    let passwordRegex = /^\d{6}$/
    return usernameRegex.test(username.value.trim()) && passwordRegex.test(password.value.trim())
}

function checkConfirmPassword(){
    let password = document.getElementById("password")
    let confirmPassword = document.getElementById("confirmPassword")
    if(confirmPassword == null){
        console.log("confirmPassword == null...")
        return
    }
    let confirmPasswordNote = document.getElementsByClassName("confirmPasswordNote")[0]
    confirmPassword.addEventListener("input", () => {
        console.log(`password: ${password.value}`)
        console.log(`confirmPassword: ${confirmPassword.value}`)
        if(password.value === confirmPassword.value){
            confirmPasswordNote.innerText = "OK"
        }else{
            confirmPasswordNote.innerText = "请保持两次输入密码一致"
        }
    })
}

function checkConfirmForm(){
    let username = document.getElementById("username")
    let password = document.getElementById("password")
    let confirmPassword = document.getElementById("confirmPassword")
    let usernameRegex = /^[\w\u3400-\u4dbf\u4e00-\u9fff]{5,10}$/
    let passwordRegex = /^\d{6}$/
    return usernameRegex.test(username.value.trim()) && passwordRegex.test(password.value.trim()) && password.value === confirmPassword.value
}