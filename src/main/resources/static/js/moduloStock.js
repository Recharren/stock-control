'use strict'
const urlServer = "http://localhost:8080";
//const urlServer = "https://taskcontrol.herokuapp.com";

// -------------------------- Acciones a ejecutar cuando se carga el documento HTML---------------------------

document.addEventListener('DOMContentLoaded', function(){
    cargarTablas(); // LLAMADO A FUNCION QUE CARGA TODOS LOS PRODUCTOS EN EL FRONT

    document.getElementById("contenedorAgregar").classList.add("invisible"); // HACE INVISIBLE EL MENU AGREGAR STOCK
    document.getElementById("contenedorConsumir").classList.add("invisible"); // HACE INVISIBLE EL MENU CONSUMIR STOCK

    let idSesion = document.getElementById("idSesion"); // ID DE USUARIO EN SESION
    
})

//-------------------------------- Funcion para cargar todos los productos en las tablas del front -----------------------

async function cargarTablas(){
    let tablaBody = document.getElementsByClassName("tablaBody"); // ARREGLO DE LAS 6 TABLAS

    for (let i = 0; i < tablaBody.length; i++) { // ITERA POR CADA TABLA DE PRODUCTOS Y CARGA LOS DATOS SEGUN CATEGORIA
        await fetch(urlServer+"/productoRest/productos/"+(i+1))
            .then(res=>res.json())
            .then(datos =>{
                if(datos!=null){
                    let info = "";

                    datos.forEach(producto => { // ITERA EN CADA PRODUCTO DE LA CATEGORIA i
                        let cadaProd = `<tr>`+
                        `<input type="number" class="codProd" value='${producto.codigo}' hidden>`+
                        `<input type="text" class="nomProd" value='${producto.nombre}' hidden>`+
                        `<th>${producto.codigo}</th>`+
                        `<th>${producto.nombre}</th>`+
                        `<th>${producto.stock}</th>`+
                        `<th>${producto.sobrante}</th>`+
                        `<th>${producto.ultModif}</th>`+
                        `<th>${producto.cantIngresada}</th>`+
                        `<th>${producto.autorUltimo}</th>`+
                        `<th><button class="btnAgregar">Agregar</button></th>`+
                        `<th><button class="btnConsumir">Consumir</button></th>`+
                        `<th><a href="/producto/editarProducto/${producto.codigo}" >Modificar</a></th>`+
                        
                        `</tr>`;

                        info += cadaProd
                    });
                    tablaBody[i].innerHTML = info;
                    if (i==(tablaBody.length-1)) {
                        asignarBotones()
                    }
                    
                } 
            })
    } 
    function asignarBotones(){
                    let codProd = document.getElementsByClassName("codProd"); // ARRAY DE CODIGOS DE PRODUCTO
                    let nomProd = document.getElementsByClassName("nomProd"); // ARRAY DE NOMBRES DE PRODUCTO
                    let btnAgregar = document.getElementsByClassName("btnAgregar"); // ARRAY DE BOTONES 'AGREGAR'
                    let btnConsumir = document.getElementsByClassName("btnConsumir"); // ARRAY DE BOTONES 'CONSUMIR'

                    for (let i = 0; i < codProd.length; i++) {
        
                        btnAgregar[i].addEventListener("click",()=>{
                            mostrarMenuAgregar(idSesion.value, codProd[i].value, nomProd[i].value)
                            document.getElementById("contenedorAgregar").classList.remove("invisible"); // HACE VISIBLE EL MENU AGREGAR STOCK
                        })
                        
                        btnConsumir[i].addEventListener("click", ()=>{
                            mostrarMenuConsumir(idSesion.value, codProd[i].value, nomProd[i].value)
                            document.getElementById("contenedorConsumir").classList.remove("invisible"); // HACE VISIBLE EL MENU CONSUMIR STOCK
                        })
                    }
    }

}


//---------------------------------------------------------------------------------------------------

function mostrarMenuAgregar(idSesion,codigoProducto,nombreProducto){
    document.getElementById("formAgregar").innerHTML = `
        <input type="number" name="idSesionAgre" value="${idSesion}" hidden>
        <input type="number" name="codigoProdAgre"  value="${codigoProducto}" hidden>
        <h3>${nombreProducto}</h3><br>

        <label for="cantAgre">Cantidad</label>
        <input type="number" name="cantAgre"><br><br>

        <label for="sobranteAgre">Sobrante</label>
        <input type="text" name="sobranteAgre"> <br>

        <button type="submit" class="btnGuardar">Guardar cambios</button>`
    document.getElementById("cerrarAgregar").addEventListener("click", ()=>{
        document.getElementById("contenedorAgregar").classList.add("invisible"); // HACE INVISIBLE EL MENU AGREGAR STOCK
    })
}

//---------------------------------------------------------------------------------------------------

function mostrarMenuConsumir(idSesion,codigoProducto,nombreProducto){
    document.getElementById("formConsumir").innerHTML = `
        <input type="number" name="idSesionCons" value="${idSesion}" hidden>
        <input type="number" name="codigoProdCons" value="${codigoProducto}" hidden>
        <h3>${nombreProducto}</h3><br>

        <label for="cantAgre">Cantidad</label>
        <input type="number" name="cantCons"><br><br>

        <label for="sobranteAgre">Sobrante</label>
        <input type="text" name="sobranteCons"> <br>

        <button type="submit" class="btnGuardar">Guardar cambios</button>`
    document.getElementById("cerrarConsumir").addEventListener("click",()=>{
        document.getElementById("contenedorConsumir").classList.add("invisible"); // HACE INVISIBLE EL MENU CONSUMIR STOCK
    })
}