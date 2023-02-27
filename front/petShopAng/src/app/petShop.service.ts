import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Pet } from './petShop';
import { environment } from 'src/environments/environment';
import { catchError, tap } from 'rxjs/operators'

@Injectable({providedIn: 'root'})
export class PetShopService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient){}

  public getOrders() : Observable<any>{
    return this.http.get<Pet[]>(`${this.apiServerUrl}/orders/get/all`)
    .pipe(
      catchError(this.handleError)
    );
  }

  public addOrder(pet: Pet): Observable<Pet> {
    return this.http.post<Pet>(`${this.apiServerUrl}/orders/add`, pet)
    .pipe(
      catchError(this.handleError)
    );
  }

  public updateOrder(pet: Pet, petId: number): Observable<Pet> {
    return this.http.put<Pet>(`${this.apiServerUrl}/orders/edit/${petId}`, pet)
    .pipe(
      catchError(this.handleError)
    );
  }

  public deleteOrder(petId: number | undefined): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/orders/delete/${petId}`)
    .pipe(
      catchError(this.handleError)
    );
  }


  handleError(error: HttpErrorResponse){
    if(error.error instanceof ErrorEvent){//erro clientSide ou internet
      console.error('An error ocurred: ' + error.error.message);
    } else{
      console.error(`Backend returned error code: ${error.status}` +
      `body: ${error.error}`
      )
    }

    return throwError(()=> new Error('There is an error ocurring'))
  }
}