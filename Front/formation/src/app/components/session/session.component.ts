import { Component, Inject, OnInit,ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AstMemoryEfficientTransformer } from '@angular/compiler';
import {MatDialog} from '@angular/material/dialog';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import { AjoutSessionComponent } from '../ajout-session/ajout-session.component';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-session',
  templateUrl: './session.component.html',
  styleUrls: ['./session.component.css']
})
export class SessionComponent implements OnInit {

  displayedColumns: string[] = ['id', 'date_debut','date_fin','lieu','nbr_participants','formateur','organisme','formations', 'actions'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private dialog: MatDialog, 
    private sessionService: SessionService) { }

  ngOnInit(): void {
    this.getAllSessions();
  }

    openSession() {
      this.dialog.open(AjoutSessionComponent, {
        width:'30%'
      }).afterClosed().subscribe(val=>{
        if(val==='save'){
          this.getAllSessions();
        }
      })
    }
  
    editSession(row:any){
      //console.log('id dom',row.domain.id)
      this.dialog.open(AjoutSessionComponent,{
        width:'30%',
        data:row
      }).afterClosed().subscribe(val=>{
        if(val==='update'){
          this.getAllSessions();
        }
    })
  }
  getAllSessions(){
       this.sessionService.getAll().subscribe({
         next:(res)=>{

         this.dataSource=new MatTableDataSource(res);
         this.dataSource.paginator=this.paginator;
         this.dataSource.sort=this.sort;
  
         },
         error:(err)=>{
          alert("Erreur dans la récupération des données");
         }
       })
    }
  
    deleteSession(id:number){
      this.sessionService.delete(id).subscribe({
        next:(res)=>{
          alert("La session est supprimé avec succès");
          this.getAllSessions();
        },
        error:()=>{
          alert("Erreur dans la suppression de session")
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
