import { CategoryItem } from "../shopcomponents/CategoryItem";

export const CategorySection: React.FC<CategoryProps> = ({
  category,
  categoryList,
  filterCategory,
}) => {
  return (
    <dl className="p-2 capitalize">
      <dt className="font-bold border-b-2 border-jalapeno">{category}</dt>
      {categoryList.map((categoryItem) => (
        <CategoryItem
          name={categoryItem.name.toLowerCase().replace("_", " ")}
          category={category}
          key={categoryItem.id}
          filterCategory={filterCategory}
        />
      ))}
    </dl>
  );
};

interface CategoryProps {
  category: string;
  filterCategory: (category: string, name: string, checked: boolean) => void;
  categoryList: { name: string; id: Number }[];
}
