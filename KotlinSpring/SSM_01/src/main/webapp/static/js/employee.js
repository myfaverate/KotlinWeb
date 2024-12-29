window.onload = () => {
    const deleteButton = document.getElementById("delete")
    const href = deleteButton?.href
    deleteButton.addEventListener("click", (event) => {
        event.preventDefault()
        sendDeleteRequest(href)
    })
}
function sendDeleteRequest(url) {
    console.log(`sendDeleteRequest -> url: ${url}`)
    const xmlHttpRequest = new XMLHttpRequest
    xmlHttpRequest.open('DELETE', url, true)
    xmlHttpRequest.timeout = 3000
    xmlHttpRequest.send()

    const refresh = document.getElementById("refresh")
    console.log(`refresh.href: ${refresh.href.toString()}`)

    xmlHttpRequest.onreadystatechange = function () {
        if (xmlHttpRequest.readyState === 4 && xmlHttpRequest.status === 200) {
            console.log(`response：${xmlHttpRequest.responseText}`)
            window.location.href = refresh.href
        }else{
            console.log(`error: ${xmlHttpRequest.responseText}`)
        }
    }
}