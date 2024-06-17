function Card({ children }) {
    return (
        <div className="card">
            {children}
        </div>
    );
}

function Avatar({ name, rating }) {
    return (
        <div>
            <p>{name}</p>
            <img
                className="avatar"
                src="https://i.imgur.com/MK3eW3As.jpg"
                alt={name}
            />
            <p>Rating: {rating}</p>
        </div>
    );
}

export default function Entry({name, rating}) {
    return (
        <Card>
            <Avatar name={name} rating={rating}/>
        </Card>
    );
}