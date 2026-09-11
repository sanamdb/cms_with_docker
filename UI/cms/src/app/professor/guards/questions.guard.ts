import { Inject, Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanDeactivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { CreateQuestionComponent } from '../components/create-question/create-question.component';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class QuestionsGuard implements CanDeactivate<CreateQuestionComponent> {

  canDeactivate(
    component: CreateQuestionComponent,
    currentRoute: ActivatedRouteSnapshot,
    currentState: RouterStateSnapshot,
    nextState?: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (!component.questionForm.dirty)
      return true;
    return Swal.fire({
      title: 'Discard changes?',
      text: 'Your edits have not been saved.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Discard',
      cancelButtonText: 'Keep editing',
      reverseButtons: true
    }).then(result => result.isConfirmed);
  }

}
