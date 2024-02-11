


function message_toast(message)
{
    const toast = document.getElementById('liveToast')
    const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toast)
    toastBootstrap.show();

    const messagetoast=document.querySelector(".toast-body");
    messagetoast.innerHTML=message;

}