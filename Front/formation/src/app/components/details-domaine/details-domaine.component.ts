import { Component, Input, OnInit } from '@angular/core';
import { DomaineService } from 'src/app/services/domaine.service'; 
import { ActivatedRoute, Router } from '@angular/router';
import { Domaine } from 'src/app/models/domaine';

@Component({
  selector: 'app-details-domaine',
  templateUrl: './details-domaine.component.html',
  styleUrls: ['./details-domaine.component.css']
})

export class DetailsDomaineComponent implements OnInit {
  @Input() viewMode = false;
  @Input() currentDomaine: Domaine = {
    libelle: '',
  };
  
  message = '';
  constructor(
    private domaineService: DomaineService,
    private route: ActivatedRoute,
    private router: Router) { }
  
  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getDomaine(this.route.snapshot.params["id"]);
    }
  }

  getDomaine(id: string): void {
    this.domaineService.get(id)
      .subscribe({
        next: (data) => {
          this.currentDomaine = data;
          console.log(data);
        },
        error: (e) => console.error(e)
    });
  }

  updateDomaine(): void {
    //this.message = '';
    this.domaineService.update(this.currentDomaine.id, this.currentDomaine)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message ? res.message : 'This domaine was updated successfully!';
        },
        error: (e) => console.error(e)
      });
  }

  deleteDomaine(): void {
    this.domaineService.delete(this.currentDomaine.id)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.router.navigate(['/domaines']);
        },
        error: (e) => console.error(e)
      });
  }
}