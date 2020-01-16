import {PostDto} from './PostDto';

export interface PageDto {
  content: PostDto[],
  totalElements: number,
  totalPages: number,
  last: boolean,
  size: number,
  number: number,
  numberOfElements: number,
  first: boolean,
  empty: boolean
}
