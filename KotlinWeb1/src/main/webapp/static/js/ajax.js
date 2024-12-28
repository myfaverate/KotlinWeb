window.onload = () => {
    sendMessage()
}

/**
 * 0	UNSENT	代理被创建，但尚未调用 open() 方法。
 * 1	OPENED	open() 方法已经被调用。
 * 2	HEADERS_RECEIVED	send() 方法已经被调用，并且头部和状态已经可获得。
 * 3	LOADING	下载中；responseText 属性已经包含部分数据。
 * 4	DONE	下载操作已完成。
 */
function sendMessage() {
    let sendMessage = document.getElementById("sendMessage")
    console.log(`sendMessage: ${sendMessage}`)
    sendMessage.onclick = () => {
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
                    }
                    break
                }
                default: {
                    console.log("default...")
                }
            }
        }
        xmlHttpRequest.open("GET", "HelloServlet?username=张书豪")
        xmlHttpRequest.send()
    }
}

