import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pet } from './petShop';
import { environment } from 'src/environments/environment';
import { tap } from 'rxjs/operators'

@Injectable({providedIn: 'root'})
export class PetShopService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient){}

  public getOrders() : Observable<any>{
    return this.http.get<Pet[]>(`${this.apiServerUrl}/orders/get/all`);
  }

  public addOrder(pet: Pet): Observable<Pet> {
    return this.http.post<Pet>(`${this.apiServerUrl}/orders/add`, pet);
  }

  public updateOrder(pet: Pet, petId: number): Observable<Pet> {
    return this.http.put<Pet>(`${this.apiServerUrl}/orders/edit/${petId}`, pet);
  }

  public deleteOrder(petId: number | undefined): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/orders/delete/${petId}`);
  }

}