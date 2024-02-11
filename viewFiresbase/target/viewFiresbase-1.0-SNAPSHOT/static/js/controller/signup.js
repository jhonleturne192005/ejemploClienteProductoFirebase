angular.module("app",[])
.controller("signController", function ($scope, $http) 
{
    $scope.url='http://localhost:8081/api/usuario';
    $scope.usuario="";
    $scope.contrasenia="";

    $scope.signup=(form)=> {
        console.log(form);
        if($scope.usuario!="" && $scope.contrasenia!=""){
            let formData=new FormData();
            formData.append("usuario",form.usuario.$viewValue);
            formData.append("contrasenia",form.contrasenia.$viewValue);
            $.ajax({
                method:"POST",
                url:$scope.url+"/insertar",
                processData:false,
                contentType: false,
                data:formData,
                beforeSend: function (xhr) {
                    console.log("cargando...");
                },
                success: function (data) {
                    location.href="/viewFiresbase/"
                },
                error: function (objXMLHttpRequest) 
                {
                    message_toast(objXMLHttpRequest.responseJSON.error);
                    console.log("error: ", objXMLHttpRequest);
                }
            });
        }else{
            message_toast("Error campos vacios");
        }
    }
});