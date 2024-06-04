import { Component } from '@angular/core';
import { ATM } from '../dominio/ATM';
import { routes } from '../../app.routes';
import { Router } from '@angular/router';
import { ServiceService } from '../../Service/service.service';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import Swal from 'sweetalert2';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-list',
  standalone: true,
  imports: [CommonModule,HttpClientModule,ReactiveFormsModule, FormsModule ],
  templateUrl: './list.component.html',
  styleUrl: './list.component.css'
})
export class ListComponent {


  atm: ATM = new ATM();
  list: ATM[];
  saldo: number;

  constructor(private router: Router, private service: ServiceService){}

 ngOnInit(): void {
  //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
  //Add 'implements OnInit' to the class.
  this.listar();
  this.getSaldo();
 }

listar(){
this.service.list().subscribe((data: any) =>{
this.list=data;
console.log("Billetes disponibles -> "+JSON.stringify(data));
});
}


abrir(){
  Swal.fire({
  title: "Retiro.",
  input: 'number',
  inputLabel: 'Ingrese la cantidad que desea retirar.',
  inputAttributes: {
    autocapitalize: "off",
    type: "number",
    step: "0.50" // Establecer el paso a 0.50
  },
  showCancelButton: true,
  confirmButtonText: "Retirar",
  showLoaderOnConfirm: true,
  preConfirm: async (monto) => {
    try {
      if (!isNaN(parseFloat(monto)) && isFinite(monto)) {

        if(monto<= this.saldo){
          this.retirar(parseFloat(monto));
        }else{
          Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Saldo insuficiente",
          });
        }


      } else {
        throw new Error("Ingrese un numero valido.");
      }
    } catch (error) {
      Swal.showValidationMessage(`
        Request failed: ${error}
      `);
    }
  },
  allowOutsideClick: () => !Swal.isLoading()
});

}



retirar(monto:number){

  let timerInterval: any;
  console.log(monto);
  this.service.retirar(monto).subscribe((data:any) =>{

    if(data.Mensaje=="Se completo el retiro"){
      this.router.navigate(['list']);
      location.reload();
      Swal.fire("Retirado");
  }else{
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: "No se pudo retirar",
    });
  }

  });

}

getSaldo(){
  this.service.getSaldo().subscribe((data: any) =>{
    this.saldo=data;
    console.log("Saldo -> "+JSON.stringify(data));
    });
}


recargar(){

  Swal.fire({
    title: "¿Deseas recargar el cajero?",
    text: "No se podra revertir la accion",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Recargar",
    cancelButtonText: 'Cancelar'
  }).then((result) => {
    if (result.isConfirmed) {
      this.service.reload().subscribe((data:any) =>{
        console.log("Saldo -> "+JSON.stringify(data));
        Swal.fire({
          title: "Recargado",
          text: "El cajero fue recargado.",
          icon: "success"
        });
        this.router.navigate(['start']);
        location.reload();
      })

    }
  });





}

}
