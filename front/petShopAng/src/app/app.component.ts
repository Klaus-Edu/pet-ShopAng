import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { NgForm } from '@angular/forms';

import { Pet } from './petShop';
import { PetShopService } from './petShop.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {

  public pets: Pet[] = [];
  public editOrder: Pet | null;
  public deleteOrder: Pet | null;
  public selectedPet: Pet;
  isModalOpen = false;
  @ViewChild('editPetModal') editPetModal: any;
  @ViewChild('blur') blur: ElementRef | undefined;


  constructor(private petShopService: PetShopService, private modalService: NgbModal) { }

  ngOnInit() {
    this.getOrders();
  }

  public getOrders(): void {
    this.petShopService.getOrders().subscribe(
      (response: Pet[]) => {
        this.pets = response;
        console.log(this.pets);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddOrder(addOrder: NgForm): void {
    document.getElementById("add-order-form")?.click();

    this.petShopService.addOrder(addOrder.value).subscribe(
      (response: Pet) => {
        console.log(response);
        this.getOrders();
        addOrder.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public onDeleteOrder(petId: number | undefined): void {
    
    this.petShopService.deleteOrder(petId).subscribe(
      (response: void) => {
        this.getOrders();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
      )
    }
    
    public onEditOrder(pet: Pet): void {
  
      this.petShopService.updateOrder(pet, pet.id).subscribe(
        (response: Pet) => {
          console.log(response);
          this.getOrders();
          this.modalService.dismissAll();
          const back = this.blur?.nativeElement;
          back?.nativeElement.classList.remove('modal-backdrop')
        },
        (error: HttpErrorResponse) => {
          alert(error.message)
        }
      )
    }
  
    openEditModal(pet: Pet) {
      this.selectedPet = pet;
      console.log(this.blur);
      const back = this.blur?.nativeElement;
      back?.classList.add('modal-backdrop');
      this.modalService.open(this.editPetModal, { size: 'lg' }).result.finally(() => {
        back?.nativeElement.classList.remove('modal-backdrop')
      });
    }
}
