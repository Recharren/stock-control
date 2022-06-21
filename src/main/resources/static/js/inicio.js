'use strict'
const urlServer = "http://localhost:8080";
//const urlServer = "https://taskcontrol.herokuapp.com";

// -------------------------- Acciones a ejecutar cuando se carga el documento HTML---------------------------

document.addEventListener('DOMContentLoaded', function (){
    let idSesion = document.getElementById("idSesion"); // ID DE USUARIO EN SESION
    let codigoProducto = document.getElementsByClassName("codigoProducto"); // ARRAY DE CODIGOS DE PRODUCTO
    let nombreProducto = document.getElementsByClassName("nombreProducto");
    let btnAgregar = document.getElementsByClassName("btnAgregar"); // ARRAY DE BOTONES 'AGREGAR'
    let btnConsumir = document.getElementsByClassName("btnConsumir"); // ARRAY DE BOTONES 'CONSUMIR'
    document.getElementById("contenedorAgregar").classList.add("invisible"); // HACE INVISIBLE EL MENU AGREGAR STOCK
    document.getElementById("contenedorConsumir").classList.add("invisible"); // HACE INVISIBLE EL MENU CONSUMIR STOCK

    for (let i = 0; i < codigoProducto.length-1; i++) {
        
        btnAgregar[i].addEventListener("click",()=>{
            mostrarMenuAgregar(idSesion, codigoProducto[i].value, nombreProducto[i].value)
            document.getElementById("contenedorAgregar").classList.remove("invisible"); // HACE VISIBLE EL MENU AGREGAR STOCK
        })
        
        btnConsumir[i].addEventListener("click", ()=>{
            mostrarMenuConsumir(idSesion, codigoProducto[i].value, nombreProducto[i].value)
            document.getElementById("contenedorConsumir").classList.remove("invisible"); // HACE VISIBLE EL MENU CONSUMIR STOCK
        })
    }
    
})

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