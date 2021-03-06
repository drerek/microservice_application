export class Item {
  itemId: number;
  name: string;
  description: string;
  ownerLogin: string;
  bookerId: string;
  priority: string;
  imageFilepath: string;
  link: string;
  dueDate: string;
  likes: number;
  tags: string[] = [];
  like: boolean;

  clone() : Item {
    let itemClone = new Item();

    itemClone.itemId = this.itemId;
    itemClone.name = this.name;
    itemClone.description = this.description;
    itemClone.ownerLogin = this.ownerLogin;
    itemClone.bookerId = this.bookerId;
    itemClone.priority = this.priority;
    itemClone.imageFilepath = this.imageFilepath;
    itemClone.link = this.link;
    itemClone.dueDate = this.dueDate;
    itemClone.likes = this.likes;
    itemClone.tags = this.tags;
    itemClone.like = this.like;
    return itemClone;
  }
}

