import Row from './Row'
import { useEffect } from 'react';

const Board = () => {
    
    useEffect(() => {
        console.log('Board Loaded');
    }, []) ;

    return (
        <>
            <Row></Row>
        </>
    )
}

export default Board;