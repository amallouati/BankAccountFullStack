import { HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { environment } from "src/environments/environment";
import { Account } from '../model/account.model';



@Injectable({ providedIn: 'root' })
export class AccountService {

    private API_URL=environment.BANKACCOUNT_API;

    constructor(public http: HttpClient) {}

    public getAllAccount() :Observable<Account[]>{
       return  this.http.get<any>(this.API_URL.concat('accounts/all'))
    }

}