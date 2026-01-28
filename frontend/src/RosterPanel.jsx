
export default function RosterPanel({team, players, loading, error, onClose, onPositionClick, positions}){
return (
<div className="modalBackdrop" onClick={onClose}>
    <div className="modalCard" onClick={(e) => e.stopPropagation()}>
        <div className="modalHeader">
            <div className="modalTeam">
                <img className="modalLogo" src={team.logo} alt={team.name} />
                <h2 className="modalTitle">{team.name}</h2>
            </div>
        <button type="button" className="modalClose" onClick={onClose}>
            <b>CLOSE</b>
        </button>
        <div className="buttonsWraper">
            {positions.map((position) => (
                <button
                key={position.id}
                type="button"
                onClick={() => onPositionClick(position)}
                title={position.name}>
                    {position.id}
                </button>
            ))}
        </div>

    </div>

    {loading && <div>Loading</div>}
    {error && <div>Error:{error}</div>}

    {!loading && !error &&(
        <ul className="playerList">
            {players.map((p) => (
                <li className="playerRow" key={p.id}>
                    <div className="colNum">No.{p.jerseyNumber ?? "-"}</div>
                    <div className="colPos">{p.position ?? "-"}</div>
                    <div className="colName">
                        <div className="name">{p.firstName} {p.lastName}</div>
                        <div className="meta">{p.college ?? "—"} - exp {p.yearsExp ?? "-"} years - {p.weight}lbs - {p.height}feet</div>
                    </div>
                    <div className="colStatus">{p.status ?? "-"}</div>
                </li>
            ))}
        </ul>
    )}
    </div>
</div>
);
}