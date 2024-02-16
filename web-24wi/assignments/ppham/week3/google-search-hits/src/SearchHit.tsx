interface SearchHitProps {
    hit: string
}
const SearchHit = (props: SearchHitProps) => {
    
    return (
        <div dangerouslySetInnerHTML={{__html: props.hit}}>
        </div>
    );
};
export default SearchHit;