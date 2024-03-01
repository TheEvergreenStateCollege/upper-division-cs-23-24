import Box from './Box'
import { useEffect } from 'react';

const Row = () => {
    
    useEffect(() => {
        console.log('Row Loaded');
    }, []) ;

    return (
        <>
            <Box></Box>
        </>
    )
}

export default Row;