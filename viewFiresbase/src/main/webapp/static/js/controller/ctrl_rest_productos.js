angular.module("app")
.controller("rest_controller_productos", function ($scope, $http) {

    $scope.productos=undefined;
    $scope.url='http://localhost:8081/api/producto';

    $scope.id="";
    $scope.nombre="";
    $scope.empresa="";
    $scope.precio_unitario="";
    $scope.descuento="";
    $scope.proveedor="";
    $scope.descripcion="";

    $(document).ready(function ()
    {
        consumer_rest();
    });


    function consumer_rest(){
        $.ajax({
            method:"POST",
            url:$scope.url+"/leer",
            processData:false,
            contentType: false,
            beforeSend: function (xhr) {
                console.log("cargando...");
            },
            success: function (data) {
                $scope.$apply(function() 
                {
                    $scope.productos=data;
                    message_toast("Información cargada correctamente");
                });
                console.log(data);
            },
            error: function (objXMLHttpRequest) 
            {
                message_toast(objXMLHttpRequest.responseJSON.error);
                console.log("error: ", objXMLHttpRequest);
            }
        });
    }


    $scope.eliminar=(id)=> {
        console.log(id);
        let formData=new FormData();
        formData.append("id",id);
        $.ajax({
            method:"POST",
            url:$scope.url+"/eliminar",
            processData:false,
            contentType: false,
            data: formData,
            beforeSend: function (xhr) {
                console.log("cargando...");
            },
            success: function (data) {
                consumer_rest();
                console.log(data);
                message_toast("Se elimino correctamente");
            },
            error: function (objXMLHttpRequest) 
            {
                message_toast(objXMLHttpRequest.responseJSON.error);
                console.log("error: ", objXMLHttpRequest);
            }
        });
    }


    $scope.actualizar_guardar=(form)=> {
        console.log(form);
        
        let formData=new FormData();
        if(form.id.$viewValue!="")
            formData.append("id",form.id.$viewValue);
        formData.append("nombre",form.nombre.$viewValue);
        formData.append("empresa",form.empresa.$viewValue);
        formData.append("precio_unitario",form.precio_unitario.$viewValue);
        formData.append("descuento",form.descuento.$viewValue);
        formData.append("proveedor",form.proveedor.$viewValue);
        formData.append("descripcion",form.descripcion.$viewValue);
        $.ajax({
            method:"POST",
            url:$scope.url+"/"+(form.id.$viewValue==""?"insertar":"actualizar"),
            processData:false,
            contentType: false,
            data:formData,
            beforeSend: function (xhr) {
                console.log("cargando...");
            },
            success: function (data) {
                $scope.$apply(function() 
                {
                    consumer_rest();
                    if(form.id.$viewValue=="")
                        message_toast("Información insertada correctamente");
                    else
                        message_toast("Información actualizada correctamente");
                });
                console.log(data);
            },
            error: function (objXMLHttpRequest) 
            {
                message_toast(objXMLHttpRequest.responseJSON.error);
                console.log("error: ", objXMLHttpRequest);
            }
        });
    }


    $scope.editar=(obj)=> 
    {
        $('#staticBackdrop').on('shown.bs.modal', function(event) {
            $scope.$apply(function() 
            {
                $scope.id=obj.id;
                $scope.nombre=obj.nombre;
                $scope.empresa=obj.empresa;
                $scope.precio_unitario=obj.precio_unitario;
                $scope.descuento=obj.descuento;
                $scope.proveedor=obj.proveedor;
                $scope.descripcion=obj.descripcion;
            });
        });
    }


    $scope.buscar=()=> 
    {
        let formData=new FormData();
        formData.append("valor",$scope.nombre);

        $.ajax({
            method:"POST",
            url:$scope.url+"/buscar",
            processData:false,
            contentType: false,
            data: formData,
            beforeSend: function (xhr) {
                console.log("cargando...");
            },
            success: function (data) {
                $scope.$apply(function() 
                {
                    $scope.productos=data;
                });
                console.log(data);
            },
            error: function (objXMLHttpRequest) 
            {
                console.log("error: ", objXMLHttpRequest);
            }
        });         
    }

    
});