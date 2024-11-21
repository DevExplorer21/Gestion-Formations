import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AstMemoryEfficientTransformer } from '@angular/compiler';
import { MatDialog } from '@angular/material/dialog';
//import { DomainComponent } from './components/domain/domain.component';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AddDomaineComponent } from '../add-domaine/add-domaine.component';
import { DomaineService } from 'src/app/services/domaine.service';

@Component({
  selector: 'app-domain',
  templateUrl: './domain.component.html',
  styleUrls: ['./domain.component.css']
})

export class DomainComponent implements OnInit {
  displayedColumns: string[] = ['id', 'libelle', 'actions'];
  dataSource!: MatTableDataSource<any>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private dialog: MatDialog,
    private domainService: DomaineService) { }

  ngOnInit(): void {
    this.getAllDomains();
    /*this.domainForm=this.formBuilder.group({
      libelle :['', Validators.required]
    });
    console.log(this.editData);
    if(this.editData)
    { this.actionsBtn="Modifier";
      this.domainForm.controls['libelle'].setValue(this.editData.libelle);
    }*/
  }


  /* addDomain(){
     console.log(this.domainForm.value);
     if(!this.editData){
       if(this.domainForm.valid){
         this.api.postDomain(this.domainForm.value)
         .subscribe({
           next:(res)=>{
             alert("Domain est ajouté avec succès");
             this.domainForm.reset();
             this.dialogref.close('save');
           },
           error:()=>{
             alert("Erreur dans la création de Domain")
           }
         })
       }
     }else{
      this.updateDomain();
     }
     }
 
     updateDomain(){
       this.api.putDomain(this.domainForm.value, this.editData.id)
       .subscribe({
         next:(res)=>{
           alert("Domaine est modifiée avec succès");
           this.domainForm.reset();
           this.dialogref.close('update');
         },
         error:()=>{
           alert("Erreur dans la modification de domaine");
         }
       })
     }*/

  openDomain() {
    this.dialog.open(AddDomaineComponent, {
      width: '30%'
    }).afterClosed().subscribe(val => {
      if (val === 'save') {
        this.getAllDomains();
      }
    })
  }

  editDomain(row: any) {
    this.dialog.open(AddDomaineComponent, {
      width: '30%',
      data: row
    }).afterClosed().subscribe(val => {
      if (val === 'update') {
        this.getAllDomains();
      }
    })
  }
  getAllDomains() {
    this.domainService.getAll().subscribe({
      next: (res) => {
        // console.log(res);
        this.dataSource = new MatTableDataSource(res);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;

      },
      error: (err) => {
        alert("Erreur dans la récupération des données");
      }
    })
  }

  deleteDomain(id: number) {
    this.domainService.delete(id).subscribe({
      next: (res) => {
        alert("Le domaine est supprimé avec succès");
        this.getAllDomains();
      },
      error: () => {
        alert("Erreur dans la suppression de domaine")
      }
    })

  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

}
