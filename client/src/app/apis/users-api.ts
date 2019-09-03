import { Apollo } from 'apollo-angular';
import gql from 'graphql-tag';

export class UsersApi {
  constructor(private apollo: Apollo) {
  }

  login(username: string, password: string) {
    const mutation = gql`
      mutation login(
        $username: String!,
        $password: String!
      ) {
        login(
          input: {
            username: $username,
            password: $password
          }
        ) {
          username
        }
      }
    `;

    return this.apollo
      .mutate({
        variables: {
          username: username,
          password: password
        },
        mutation
      }).toPromise();
  }
}
