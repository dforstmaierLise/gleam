import Entry from './Entry';

export default function Gallery(){
    return (
        <section>
            <h1>Amazing games</h1>
            <Entry name={"Unreal Tournament"} rating={3} />
            <Entry name={"Metal Gear Solid"} rating={4} />
        </section>
    );
}
