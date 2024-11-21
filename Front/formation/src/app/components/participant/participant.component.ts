import { Component, Inject, OnInit,ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AstMemoryEfficientTransformer } from '@angular/compiler';
import {MatDialog} from '@angular/material/dialog';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import { AjoutParticipantComponent } from '../ajout-participant/ajout-participant.component';
import { ParticipantService } from 'src/app/services/participant.service';

@Component({
  selector: 'app-participant',
  templateUrl: './participant.component.html',
  styleUrls: ['./participant.component.css']
})
export class ParticipantComponent implements OnInit {

  displayedColumns: string[] = ['id', 'nom','prenom','email','telephone','type','pays','profil', 'actions'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private dialog: MatDialog, 
    private participantService: ParticipantService) { }

  ngOnInit(): void {
    this.getAllParticipants();
  }

    openParticipant() {
      this.dialog.open(AjoutParticipantComponent, {
        width:'30%'
      }).afterClosed().subscribe(val=>{
        if(val==='save'){
          this.getAllParticipants();
        }
      })
    }
  
    editParticipant(row:any){
      this.dialog.open(AjoutParticipantComponent,{
        width:'30%',
        data:row
      }).afterClosed().subscribe(val=>{
        if(val==='update'){
          this.getAllParticipants();
        }
    })
  }
    getAllParticipants(){
       this.participantService.getAll().subscribe({
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
  
    deleteParticipant(id:number){
      this.participantService.delete(id).subscribe({
        next:(res)=>{
          alert("Le participant est supprimé avec succès");
          this.getAllParticipants();
        },
        error:()=>{
          alert("Erreur dans la suppression du participant")
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
