import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ATM } from '../Components/dominio/ATM';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {

  constructor(private http: HttpClient) { }

  urlListar='http://localhost:9000/api/atm/available';
  urlSaldo='http://localhost:9000/api/atm/saldo';
  urlRetirar='http://localhost:9000/api/atm/retiro';
  urlReload='http://localhost:9000/api/atm/reload';




  list(){
    return this.http.get<ATM[]>(this.urlListar);
  }
  getSaldo(){
    return this.http.get<Number>(this.urlSaldo);
  }

  reload(){
    return this.http.get(this.urlReload);
  }

  retirar(monto:Number){
    return this.http.post<Number>(this.urlRetirar, { monto }, { headers: { 'Content-Type': 'application/json' } });
  }


}
