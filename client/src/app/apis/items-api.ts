import { Apollo } from 'apollo-angular';
import gql from 'graphql-tag';
import { ItemModel } from '../models/item';

export class ItemsApi {
  constructor(private apollo: Apollo) {
  }

  create(item: ItemModel) {
    const mutation = gql`
      mutation addItem(
        $category: String,
        $type: String,
        $brand: String,
        $description: String
      ) {
        addItem(
          input: {
            category: $category,
            type: $type,
            brand: $brand,
            description: $description
          }
        ) {
          id
          created
          updated
          brand
          category
          description
        }
      }
    `;

    return this.apollo
      .mutate({
        variables: {
          category: item.category,
          type: item.type,
          brand: item.brand,
          description: item.description
        },
        mutation
      }).toPromise();
  }

  get(id: string) {
    const query = gql`
      query getItem (
        $id: ID!
      ) {
        item(
          id: $id
        ) {
          id
          created
          updated
          brand
          type
          category
          description
          owner {
            firstName
            lastName
            email
            mobileNumber
            typeOfId
            idNumber
          }
        }
      }
    `;

    return this.apollo
      .query({
        variables: {
          id
        },
        query
      }).toPromise();
  }

  list(isRedeemed: boolean) {
    const query = gql`
      query listItems(
        $isRedeemed: Boolean
      ) {
        items(
          isRedeemed: $isRedeemed
        ) {
          total
          data {
            ... on Item {
              id
              created
              updated
              category
              type
              brand
            }
          }
        }
      }
    `;


    return this.apollo
      .query({
        variables: {
          isRedeemed
        },
        query
      }).toPromise();
  }

  listAll() {
    const query = gql`
      query listItems {
        allItems {
          total
          data {
            ... on Item {
              category
              isRedeemed
            }
          }
        }
      }
    `;


    return this.apollo
      .query({
        query
      }).toPromise();
  }

  redeem(item: any) {
    const mutation = gql`
      mutation redeemItem(
        $itemId: ID,
        $firstName: String,
        $lastName: String,
        $email: String,
        $mobileNumber: String,
        $typeOfId: String,
        $idNumber: String
      ) {
        redeemItem(
          input: {
            itemId: $itemId,
            firstName: $firstName,
            lastName: $lastName,
            email: $email,
            mobileNumber: $mobileNumber,
            typeOfId: $typeOfId,
            idNumber: $idNumber
          }
        ) {
          id
          created
          updated
          brand
          type
          category
          description
        }
      }
    `;

    return this.apollo
      .mutate({
        variables: {
          itemId: item.itemId,
          firstName: item.owner.firstName,
          lastName: item.owner.lastName,
          email: item.owner.email,
          mobileNumber: item.owner.mobileNumber,
          typeOfId: item.owner.typeOfId,
          idNumber: item.owner.idNumber
        },
        mutation
      }).toPromise();
  }

  update(item: ItemModel) {
    const mutation = gql`
      mutation updateItem(
        $id: ID,
        $category: String,
        $type: String,
        $brand: String,
        $description: String
      ) {
        updateItem(
          input: {
            id: $id,
            category: $category,
            type: $type,
            brand: $brand,
            description: $description
          }
        ) {
          id
          created
          updated
          brand
          category
          description
        }
      }
    `;

    return this.apollo
      .mutate({
        variables: {
          id: item.id,
          category: item.category,
          type: item.type,
          brand: item.brand,
          description: item.description
        },
        mutation
      }).toPromise();
  }
}
