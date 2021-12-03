import { HttpClient, HttpEvent } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { environment } from "src/environments/environment";
import { Account } from '../model/account.model';
import { Operation } from '../model/operation.model';



@Injectable({ providedIn: 'root' })
export class OperationService {

    private API_URL=environment.BANKACCOUNT_API;

    constructor(public http: HttpClient) {}

    public findAllOperationsByIdAccount(accountId:number) :Observable<Operation[]>{
       return  this.http.get<any>(this.API_URL.concat(`operation/${accountId}/all`))
    }


    create(accountId:number,operation: Operation): any {
        
        return this.http.post<Operation>(this.API_URL.concat(`operation/${accountId}/add`), operation);
          
      }

}