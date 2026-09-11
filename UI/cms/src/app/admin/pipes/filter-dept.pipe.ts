import { Pipe, PipeTransform } from '@angular/core';
import { Department } from '../interfaces/Department';

@Pipe({
  name: 'filterDept'
})
export class FilterDeptPipe implements PipeTransform {

  transform(departments: Department[], param: number): Department[] | null {
    console.log(param);
    return departments?departments.filter(dept => dept.duration>=param) : null;
  }

}
