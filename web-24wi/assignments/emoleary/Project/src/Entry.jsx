import { useState, useEffect } from "react";

const Entry = (data) => {
    const [categoryName, setCategoryName] = useState(data.section[0])
    const [options, setOptions] = useState(data.section.slice(1))

    useEffect(() => {
        setInfo(data)
    }, [data])

  return (
    <>
        <p>{data.section[0]}</p>

    </>
  );
};

export default Entry;