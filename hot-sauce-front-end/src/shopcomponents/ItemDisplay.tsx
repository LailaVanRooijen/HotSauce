import { useState } from "react";
import { ItemImage } from "./ItemImage";
import axios from "axios";

export const ItemDisplay: React.FC<ItemDisplayProps> = ({ item }) => {
  const [quantity, setQuantity] = useState<number>(1);

  const addToCart = (itemId: number, quantity: number) => {
    console.log("id: ", itemId, " quantity: ", quantity);
    axios
      .post("http://localhost:8080/hot-sauce-shop/cart-items", {
        id: itemId,
        quantity: quantity,
      })
      .then((response) => console.log(response))
      .catch((err) => console.error(err));
  };

  return (
    <li className="gap-2 p-10 h-96 rounded-md shadow-custom-shadow-jalapeno grid grid-rows-6 hover:bg-jalapeno-light cursor-pointer">
      <div id="top-row" className="row-span-4 grid grid-cols-6">
        <div
          className="items-center flex p-2
 col-span-2"
        >
          <ItemImage />
        </div>
        <div className="col-span-4 p-2 flex justify-between flex-col text-center">
          <h6 className="text-xl font-bold">{item.name}</h6>
          <p className="mt-2">{item.description}</p>
        </div>
      </div>
      <div
        id="bottom-row"
        className="row-span-2 flex items-center justify-between"
      >
        <div className="flex justify-between items-center">
          <p>€ {item.price}</p>
          <input
            type="number"
            className="rounded-md px-1 border-2 w-1/4"
            value={quantity}
            onChange={(e) => {
              if (Number(e.target.value) < 1 || Number(e.target.value) > 99) {
                console.error("amount must be between 1 and 99");
              } else {
                setQuantity(Number(e.target.value));
              }
            }}
          />
        </div>
        <button
          onClick={() => {
            addToCart(item.id, quantity);
          }}
          className="border-2 border-jalapeno text-jalapeno bg-white rounded-md flex place-content-between h-fit px-4 py-2 font-bold hover:bg-jalapeno hover:text-white"
        >
          Add to Cart
        </button>
      </div>
    </li>
  );
};

// interfaces
interface ItemDisplayProps {
  item: {
    id: number;
    name: string;
    description: string;
    price: number;
  };
  addToCart: (item: string, quantity: number) => void;
}
