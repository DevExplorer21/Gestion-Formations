import { Component, Inject, OnInit,ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AstMemoryEfficientTransformer } from '@angular/compiler';
import {MatDialog} from '@angular/material/dialog';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import { AjoutFormationComponent } from '../ajout-formation/ajout-formation.component';
import { FormationService } from 'src/app/services/formation.service';

@Component({
  selector: 'app-formation',
  templateUrl: './formation.component.html',
  styleUrls: ['./formation.component.css']
})
export class FormationComponent implements OnInit {

  displayedColumns: string[] = ['id', 'titre','annee','duree','budget','nb_session','type','domain', 'actions'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private dialog: MatDialog, 
    private formationService: FormationService) { }

  ngOnInit(): void {
    this.getAllFormations();
  }

    openFormation() {
      this.dialog.open(AjoutFormationComponent, {
        width:'30%'
      }).afterClosed().subscribe(val=>{
        if(val==='save'){
          this.getAllFormations();
        }
      })
    }
  
    editFormation(row:any){
      console.log('id dom',row.domain.id)
      this.dialog.open(AjoutFormationComponent,{
        width:'30%',
        data:row
      }).afterClosed().subscribe(val=>{
        if(val==='update'){
          this.getAllFormations();
        }
    })
  }
    getAllFormations(){
       this.formationService.getAll().subscribe({
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
  
    deleteFormation(id:number){
      this.formationService.delete(id).subscribe({
        next:(res)=>{
          alert("La Formation est supprimé avec succès");
          this.getAllFormations();
        },
        error:()=>{
          alert("Erreur dans la suppression de formation")
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
