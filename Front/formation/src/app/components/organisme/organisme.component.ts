import { Component, Inject, OnInit,ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AstMemoryEfficientTransformer } from '@angular/compiler';
import {MatDialog} from '@angular/material/dialog';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import { AjoutOrganismeComponent } from '../ajout-organisme/ajout-organisme.component';
import { OrganismeService } from 'src/app/services/organisme.service';


@Component({
  selector: 'app-organisme',
  templateUrl: './organisme.component.html',
  styleUrls: ['./organisme.component.css']
})
export class OrganismeComponent implements OnInit {

  displayedColumns: string[] = ['id', 'libelle', 'actions'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  constructor(
    private dialog: MatDialog, 
    private organismeService: OrganismeService) { }
 
  ngOnInit(): void {
    this.getAllOrganismes();

  }

    openOrganisme() {
      this.dialog.open(AjoutOrganismeComponent, {
        width:'30%'
      }).afterClosed().subscribe(val=>{
        if(val==='save'){
          this.getAllOrganismes();
        }
      })
    }
  
    editOrganisme(row:any){
      this.dialog.open(AjoutOrganismeComponent,{
        width:'30%',
        data:row
      }).afterClosed().subscribe(val=>{
        if(val==='update'){
          this.getAllOrganismes();
        }
    })
  }
    getAllOrganismes(){
       this.organismeService.getAll().subscribe({
         next:(res)=>{
         // console.log(res);
         this.dataSource=new MatTableDataSource(res);
         this.dataSource.paginator=this.paginator;
         this.dataSource.sort=this.sort;
  
         },
         error:(err)=>{
          alert("Erreur dans la récupération des données");
         }
       })
    }
  
    deleteOrganisme(id:number){
      this.organismeService.delete(id).subscribe({
        next:(res)=>{
          alert("L'organisme est supprimé avec succès");
          this.getAllOrganismes();
        },
        error:()=>{
          alert("Erreur dans la suppression de l'organisme")
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
